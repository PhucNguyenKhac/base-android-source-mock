package com.example.android.ui.bottom_nav_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.R
import com.example.android.adapter.ArtistAdapter
import com.example.android.databinding.FragmentArtistBinding
import com.example.android.model.Artist

class ArtistFragment : Fragment() {

    private val artistBinding: FragmentArtistBinding by lazy {
        FragmentArtistBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        displayListArtists()
        // Inflate the layout for this fragment
        return artistBinding.root
    }

    private fun displayListArtists() {
        val layout = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        artistBinding.rcvData.layoutManager = layout
        val adapter = ArtistAdapter(getListArtist())
        artistBinding.rcvData.adapter = adapter
    }

    private fun getListArtist(): List<Artist> {
        val list = mutableListOf<Artist>()

        list.add(Artist(R.drawable.img_artist_test, "Adele",43877516 ))
        list.add(Artist(R.drawable.img_artist_test, "Adele",43877516 ))
        list.add(Artist(R.drawable.img_artist_test, "Adele",43877516 ))
        list.add(Artist(R.drawable.img_artist_test, "Adele",43877516 ))
        list.add(Artist(R.drawable.img_artist_test, "Adele",43877516 ))
        list.add(Artist(R.drawable.img_artist_test, "Adele",43877516 ))

        return list
    }

}