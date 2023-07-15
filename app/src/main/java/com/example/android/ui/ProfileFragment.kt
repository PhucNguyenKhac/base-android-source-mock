package com.example.android.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.example.android.adapter.MostPlayedSongAdapter
import com.example.android.model.MostPlayedSong

class ProfileFragment : AppCompatActivity() {

    private  lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<MostPlayedSong>
    lateinit var imageId:Array<Int>
    lateinit var nameMPS:Array<String>
    lateinit var artistMPS:Array<String>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_profile)

        imageId = arrayOf(
            R.drawable.ran,
            R.drawable.taylor,
            R.drawable.harry,
        )
        nameMPS = arrayOf(
            "Dekat Di Hati",
            "Bigger Than The Whole",
            "Matilda"

        )
        artistMPS = arrayOf(
            "RAN",
            "Taylor Swift",
            "Harry Harry"

        )

        newRecyclerView=findViewById(R.id.recyclerView)
        //newRecyclerView.LayoutManager = LinearLayoutManager(this)

        newRecyclerView.setHasFixedSize(true)
        newArrayList = arrayListOf<MostPlayedSong>()
        getUserdata()
    }
    private fun getUserdata(){
        for(i in imageId.indices){
            val mostPlayedSong = MostPlayedSong(artistMPS[i],nameMPS[i],imageId[i])
            newArrayList.add(mostPlayedSong)
        }

        newRecyclerView.adapter = MostPlayedSongAdapter(newArrayList)
    }



}