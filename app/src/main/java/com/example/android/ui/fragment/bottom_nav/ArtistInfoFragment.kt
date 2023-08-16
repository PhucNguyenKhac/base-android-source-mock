package com.example.android.ui.fragment.bottom_nav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.android.R
import com.example.android.adapter.AlbumAdapter
import com.example.android.adapter.TopSongOfArtistAdapter
import com.example.android.databinding.FragmentArtistInfoBinding
import com.example.android.model.Artist
import com.example.android.ui.fragment.BaseFragment
import com.example.android.viewmodel.ChannelViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class ArtistInfoFragment : BaseFragment(), HasAndroidInjector {

    @Inject
    internal lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private val binding: FragmentArtistInfoBinding by lazy {
        FragmentArtistInfoBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ChannelViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.imgBack.setOnClickListener {
            findNavController().navigate(R.id.action_artistInfoFragment_to_homepageFragment)
        }

        initUI()
        return binding.root
    }

    private fun initUI() {
        val bundle = arguments
        val artist = bundle?.getSerializable("artist") as Artist
        binding.tvArtistName.text = artist.nameArtist
        Glide.with(this).load(artist.imageArtist).into(binding.artistImg)

        viewModel.getChannelInfo(artist.channelId)
        viewModel.channel.observe(viewLifecycleOwner) {
            it.items?.forEach { channel ->
                binding.tvFollowers.text = channel.statistic?.subscriberCount
                binding.tvListeners.text = channel.statistic?.viewCount
                Glide.with(this).load(channel.snippet?.thumbnails?.high?.url).into(binding.artistImg)
            }
        }
        displayListAlbums(artist.channelId)
        displayListSongs(artist.channelId)
    }

    private fun displayListAlbums(channelId: String) {
        val layout = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewAlbum.layoutManager = layout
        val adapter = AlbumAdapter()
        binding.recyclerViewAlbum.adapter = adapter

        viewModel.getAlbumArtistInfo(channelId)
        viewModel.albumArtistInfoList.observe(viewLifecycleOwner){ list ->
            adapter.submitList(list)
        }
    }

    private fun displayListSongs(channelId: String) {
        val layout = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewSongs.layoutManager = layout
        val adapter = TopSongOfArtistAdapter()
        binding.recyclerViewSongs.adapter = adapter

        viewModel.getSongArtistInfo(channelId)
        viewModel.songArtistInfoList.observe(viewLifecycleOwner){ list ->
            adapter.submitList(list)
        }
    }


    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}