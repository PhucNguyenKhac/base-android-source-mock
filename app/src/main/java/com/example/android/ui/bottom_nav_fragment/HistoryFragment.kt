package com.example.android.ui.bottom_nav_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.R
import com.example.android.adapter.HistoryAdapter
import com.example.android.databinding.FragmentHistoryBinding
import com.example.android.model.Song

class HistoryFragment:Fragment() {
    private var isExpanded = false
    private val historyBinding: FragmentHistoryBinding by lazy {
        FragmentHistoryBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return historyBinding.root
    }

    private fun displayListPlaylists() {
        historyBinding.showTvToday.text = "See all ${getPlayListToday().size} played"
        historyBinding.showTvYesterday.text = "See all ${getPlayListYesterday().size} played"


        val musicAdapterToday = HistoryAdapter(getPlayListToday().subList(0, 2))
        historyBinding.rcvToday.adapter = musicAdapterToday
        historyBinding.rcvToday.hasFixedSize()
        historyBinding.rcvToday.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val musicAdapterYesterday = HistoryAdapter(getPlayListYesterday().subList(0, 2))
        historyBinding.rcvYesterday.adapter = musicAdapterYesterday
        historyBinding.rcvYesterday.hasFixedSize()
        historyBinding.rcvYesterday.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        historyBinding.showTvToday.setOnClickListener {
            if(isExpanded){
                musicAdapterToday.setData(getPlayListToday().subList(0,2))
                historyBinding.showTvToday.text = "See all ${getPlayListToday().size} played"

            }else{
                musicAdapterToday.setData(getPlayListToday())
                historyBinding.showTvToday.text = "Hide the playlist"
            }
            isExpanded = !isExpanded
        }

        historyBinding.showTvYesterday.setOnClickListener {
            if(isExpanded){
                musicAdapterYesterday.setData(getPlayListYesterday().subList(0,2))
                historyBinding.showTvYesterday.text = "See all ${getPlayListYesterday().size} played"

            }else{
                musicAdapterYesterday.setData(getPlayListYesterday())
                historyBinding.showTvYesterday.text = "Hide the playlist"
            }
            isExpanded = !isExpanded
        }

    }




    private fun getPlayListToday() : List<Song>{
        val musicListToday = mutableListOf<Song>()

        musicListToday.add(Song(R.drawable.hi_vi, "Dekat Di Hati", "RAN"))
        musicListToday.add(Song(R.drawable.o_la_la, "DDDDD", "FFFF"))
        musicListToday.add(Song(R.drawable.ran, "meomeome", "dsfdfd"))
        musicListToday.add(Song(R.drawable.o_la_la, "lacludiidid", "ahihihahah"))
        musicListToday.add(Song(R.drawable.hi_vi, "ùaijanf", "RAđfdN"))
        musicListToday.add(Song(R.drawable.ran, "Dekat ádasdasDi Hati", "RANsssd"))
        musicListToday.add(Song(R.drawable.o_la_la, "Deksdfsdfat Di Hati", "RAdfdfN"))
        musicListToday.add(Song(R.drawable.hi_vi, "Dekat Di Hatgđi", "RsdfsdfAN"))
        musicListToday.add(Song(R.drawable.ran, "Dekat Di Hsfsdfsdfati", "RANđ"))
        musicListToday.add(Song(R.drawable.o_la_la, "Dekadfdsfsdfst Di Hati", "RANssd"))
        musicListToday.add(Song(R.drawable.hi_vi, "Dekat Didfd Hati", "RANđfdf"))
        return musicListToday
    }

    private fun getPlayListYesterday() : List<Song>{
        val musicListYesterday = mutableListOf<Song>()

        musicListYesterday.add(Song(R.drawable.hi_vi, "Dekat Di Hati", "RAN"))
        musicListYesterday.add(Song(R.drawable.o_la_la, "DDDDD", "FFFF"))
        musicListYesterday.add(Song(R.drawable.ran, "meomeome", "dsfdfd"))
        musicListYesterday.add(Song(R.drawable.o_la_la, "lacludiidid", "ahihihahah"))
        musicListYesterday.add(Song(R.drawable.hi_vi, "ùaijanf", "RAđfdN"))
        musicListYesterday.add(Song(R.drawable.ran, "Dekat ádasdasDi Hati", "RANsssd"))
        musicListYesterday.add(Song(R.drawable.o_la_la, "Deksdfsdfat Di Hati", "RAdfdfN"))
        musicListYesterday.add(Song(R.drawable.hi_vi, "Dekat Di Hatgđi", "RsdfsdfAN"))
        musicListYesterday.add(Song(R.drawable.ran, "Dekat Di Hsfsdfsdfati", "RANđ"))
        musicListYesterday.add(Song(R.drawable.o_la_la, "Dekadfdsfsdfst Di Hati", "RANssd"))
        musicListYesterday.add(Song(R.drawable.hi_vi, "Dekat Didfd Hati", "RANđfdf"))
        return musicListYesterday
    }
}