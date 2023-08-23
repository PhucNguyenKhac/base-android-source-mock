package com.example.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.model.Artist
import javax.inject.Inject
import com.example.domain.result.Result
import com.example.domain.channel.ChannelUseCase
import com.example.domain.channel.SearchChannelResponseDomain
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ChannelViewModel @Inject constructor(
    private val channelUseCase: ChannelUseCase,
    private val db: FirebaseFirestore
) : ViewModel() {
    private val mutableIsLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = mutableIsLoading

    private val mutableChannelResult = MutableLiveData<Boolean>()
    val channelResult: LiveData<Boolean>
        get() = mutableChannelResult

    private val _channel = MutableLiveData<SearchChannelResponseDomain>()
    val channel = _channel

    private val _artistList = MutableLiveData<List<Artist>>()
    val artistList = _artistList

    private val _artistsFromFirestore = mutableListOf<Artist>()

    private val convertList = mutableListOf<Artist>()

    private fun startLoading() {
        mutableIsLoading.postValue(true)
    }

    private fun stopLoading() {
        mutableIsLoading.postValue(false)
    }

    init {
        getInfo()
        loadArtistsFromFirestore()
    }

    private fun getInfo() {
        viewModelScope.launch {
            val documentArtistCount = getDocumentCountInCollection("Artist")
            channelUseCase.invoke()
                .onStart {
                    startLoading()
                }.collect {
                    stopLoading()
                    when (it) {
                        is Result.Success -> {
                            mutableChannelResult.value = true
                            if (_artistsFromFirestore.isEmpty()) {
                                _artistList.value = convertToArtistList(it.data)
                                saveDataToFirestore(it.data)
                                Log.e("messi", "get from if")
                            } else if (_artistsFromFirestore.size < documentArtistCount) {
                                deleteCollection("SearchChannel")
                                _artistList.value = convertToArtistList(it.data)
                                saveDataToFirestore(it.data)
                                Log.e("messi", "adding missing artists")
                            } else {
                                _artistList.value = _artistsFromFirestore
                                Log.e("messi", "get from else")

                            }
                        }
                        else -> mutableChannelResult.value = false
                    }
                }
        }
    }

    private fun deleteCollection(collectionPath: String): Task<Void> {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection(collectionPath)

        return collectionRef.get()
            .onSuccessTask { querySnapshot ->
                val batch = db.batch()
                for (document in querySnapshot) {
                    batch.delete(document.reference)
                }
                batch.commit()
            }
    }

    private fun convertToArtistList(searchChannelResponseDomain: SearchChannelResponseDomain): List<Artist> {
        searchChannelResponseDomain.items?.forEach { item ->
            val snippet = item.snippet
            if (snippet != null) {
                val image = snippet.thumbnails?.high?.url.toString()
                val name = snippet.title.toString()
                val description = snippet.description.toString()

                convertList.add(Artist(image, name, description))
            }
        }
        return convertList
    }

    private fun saveDataToFirestore(data: SearchChannelResponseDomain) {
        val artistsCollection = db.collection("SearchChannel")

        data.items?.forEach { item ->
            val snippet = item.snippet
            if (snippet != null) {
                val artistData = hashMapOf(
                    "title" to snippet.title,
                    "description" to snippet.description,
                    "customUrl" to snippet.customUrl,
                    "thumbnails" to snippet.thumbnails?.high?.url,
                    "publishedAt" to snippet.publishedAt,
                    "country" to snippet.country,
                    "channelId" to snippet.channelId
                )

                artistsCollection.add(artistData)

            }
        }
    }

    private fun loadArtistsFromFirestore() {
        val searchChannelCollection = db.collection("SearchChannel")
        searchChannelCollection.get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    val imageArtist = document.data["thumbnails"].toString()
                    val nameArtist = document.data["title"].toString()
                    val descriptionArtist = document.data["description"].toString()

                    val artist = Artist(imageArtist, nameArtist, descriptionArtist)
                    _artistsFromFirestore.add(artist)
                }
            }
            .addOnFailureListener {

            }
    }

    private suspend fun getDocumentCountInCollection(collectionName: String): Int {
        val artistsCollection = db.collection(collectionName)
        val documentCount: Int
        val querySnapshot = artistsCollection.get().await()

        documentCount = querySnapshot.size()
        Log.e("messi", documentCount.toString())
        return documentCount
    }

}