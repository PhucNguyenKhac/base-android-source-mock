package com.example.android.ui.fragment.bottom_nav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.R
import com.example.android.adapter.AlbumAdapter
import com.example.android.adapter.SongInAlbumAdapter
import com.example.android.databinding.FragmentArcticsBinding
import com.example.android.model.Album
import com.example.android.model.SongInAlbum

class ArcticsFragment : Fragment() {
    private val articBinding: FragmentArcticsBinding by lazy {
        FragmentArcticsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        displayListAlbums()
        displayListSongs()
        return articBinding.root
    }

    private fun displayListAlbums() {
        val layout = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        articBinding.recyclerViewAlbum.layoutManager = layout
        val adapter = AlbumAdapter(getListAlbum())
        articBinding.recyclerViewAlbum.adapter = adapter
    }

    private fun getListAlbum(): List<Album> {
        val list = mutableListOf<Album>()

        list.add(Album(R.drawable.album_image_1, "Manusia", 2022))
        list.add(Album(R.drawable.album_image_1, "Manusia", 2022))
        list.add(Album(R.drawable.album_image_1, "Manusia", 2022))
        list.add(Album(R.drawable.album_image_1, "Manusia", 2022))
        list.add(Album(R.drawable.album_image_1, "Manusia", 2022))
        list.add(Album(R.drawable.album_image_1, "Manusia", 2022))
        list.add(Album(R.drawable.album_image_1, "Manusia", 2022))
        return list
    }

    private fun displayListSongs() {
        val layout = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        articBinding.recyclerViewSongs.layoutManager= layout
        val adapter = SongInAlbumAdapter(getListSongs())
        articBinding.recyclerViewSongs.adapter = adapter
    }

    private fun getListSongs(): List<SongInAlbum>{
        val list = mutableListOf<SongInAlbum>()

        list.add(SongInAlbum(R.drawable.album_image_1,"Hati-Hati di Jalan"))
        list.add(SongInAlbum(R.drawable.album_image_1,"Hati-Hati di Jalan"))
        list.add(SongInAlbum(R.drawable.album_image_1,"Hati-Hati di Jalan"))
        list.add(SongInAlbum(R.drawable.album_image_1,"Hati-Hati di Jalan"))
        list.add(SongInAlbum(R.drawable.album_image_1,"Hati-Hati di Jalan"))
        list.add(SongInAlbum(R.drawable.album_image_1,"Hati-Hati di Jalan"))
        list.add(SongInAlbum(R.drawable.album_image_1,"Hati-Hati di Jalan"))
        return list
    }
}