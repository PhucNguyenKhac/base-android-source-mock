package com.example.external.channel

import android.util.Log
import com.example.domain.di.DefaultDispatcher
import com.example.domain.result.Result
import com.example.domain.channel.ChannelRepository
import com.example.domain.channel.SearchChannelResponseDomain
import com.example.external.provider.BaseProvider
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Named

class ChannelProvider @Inject constructor(
    private val channelService: ChannelService,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    @Named("snippet") private val snippet: String,
    @Named("apiKey") private val apiKey: String,
    @Named("channelType") private val channelType: String,
    @Named("defaultItemsPerPage") private val defaultItemsPerPage: Int,
    @Named("order") private val order: String
) : ChannelRepository, BaseProvider(defaultDispatcher) {

    private val listChannelInfo = mutableListOf<SearchChannelEntity>()

    override suspend fun searchChannelInfo(): Flow<Result<SearchChannelResponseDomain>> {
        return flow {
            emit(safeApiCall {
                val artistNames = getData()
                for (name in artistNames) {
                    val response = channelService.searchChannelInfo(
                        snippet,
                        apiKey,
                        channelType,
                        name,
                        defaultItemsPerPage,
                        order
                    )
                    listChannelInfo.add(response)
                }
                listChannelInfo
            })
        }.map {
            when (it) {
                is Result.Success -> {
                    val responseItems = mutableListOf<SearchChannelResponseDomain.Items>()

                    for (entity in it.data) {
                        val mappedItems = entity.items.map { item ->
                            SearchChannelResponseDomain.Items(
                                snippet = item.snippet.mapSnippetYtToDomain(item.snippet)
                            )
                        }
                        responseItems.addAll(mappedItems)
                    }

                    Result.Success(SearchChannelResponseDomain(items = responseItems))
                }

                else -> Result.Success(SearchChannelResponseDomain())
            }
        }
    }

    private suspend fun getData(): List<String> {
        val db = FirebaseFirestore.getInstance()
        val artistNames = mutableListOf<String>()

        try {
            val result = db.collection("Artist").get().await()
            for (document in result) {
                val artistName = document.getString("name")
                artistName?.let {
                    artistNames.add(it)
                }
            }
            Log.e("error", artistNames.toString())
        } catch (exception: Exception) {
            Log.e("error", "Error getting documents.", exception)
        }

        return artistNames
    }


}