package com.example.android.ui.bottom_nav_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.R
import com.example.android.adapter.MostPlayedSongAdapter
import com.example.android.databinding.FragmentProfileBinding
import com.example.android.model.MostPlayedSong

class ProfileFragment : Fragment() {

    private val profileBinding: FragmentProfileBinding by lazy {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        displayListMostPlayedSong()
        // Inflate the layout for this fragment
        return profileBinding.root
    }

    private fun displayListMostPlayedSong() {
        val layout = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        profileBinding.recyclerViewMps.layoutManager = layout
        val adapter = MostPlayedSongAdapter(getListMPS())
        profileBinding.recyclerViewMps.adapter = adapter
    }

    private fun getListMPS(): List<MostPlayedSong> {
        val list = mutableListOf<MostPlayedSong>()

        list.add(MostPlayedSong("SongABC", "Adele", R.drawable.ran))
        list.add(MostPlayedSong("SongABC", "Adele", R.drawable.ran))
       list.add(MostPlayedSong("SongABC", "Adele", R.drawable.ran))
        list.add(MostPlayedSong("SongABC", "Adele", R.drawable.ran))


        return list
    }

}







