package com.example.android.ui.fragment.bottom_nav

import com.example.android.adapter.SongAdapter
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.forEach
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.android.R
import com.example.android.adapter.AlbumAdapter
import com.example.android.databinding.FragmentArtistInfoBinding
import com.example.android.listeners.IOnClickSongItemListener
import com.example.android.model.Artist
import com.example.android.model.Song
import com.example.android.ui.fragment.BaseFragment
import com.example.android.viewmodel.ChannelViewModel
import com.maxrave.kotlinyoutubeextractor.State
import com.maxrave.kotlinyoutubeextractor.YTExtractor
import com.maxrave.kotlinyoutubeextractor.YtFile
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

//        viewModel.getChannelInfo(artist.channelId)
//        viewModel.channel.observe(viewLifecycleOwner) {
//            it.items?.forEach { channel ->
//                val subCount = channel.statistic?.subscriberCount?.toLong()
//                val viewCount = channel.statistic?.viewCount?.toLong()
//                binding.tvFollowers.text = subCount?.let { it1 -> formatNumber(it1) }
//                binding.tvListeners.text = viewCount?.let { it1 -> formatNumber(it1) }
//                Glide.with(this).load(channel.snippet?.thumbnails?.medium?.url)
//                    .into(binding.artistImg)
//            }
//        }

        displayListAlbums(artist.channelId)
        displayListSongs(artist.channelId)
    }

    private fun displayListAlbums(channelId: String) {
        val layout = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewAlbum.layoutManager = layout
        val adapter = AlbumAdapter()
        binding.recyclerViewAlbum.adapter = adapter

//        viewModel.getAlbumArtistInfo(channelId)
//        viewModel.albumArtistInfoList.observe(viewLifecycleOwner) { list ->
//            adapter.submitList(list)
//        }
    }

    private fun displayListSongs(channelId: String) {
        val layout = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewSongs.layoutManager = layout
        val adapter = SongAdapter(object : IOnClickSongItemListener {
            override fun onClickItemArtist(artist: Artist) {
                TODO("Not yet implemented")
            }

            override fun onClickItemSong(song: Song) {
                Log.e("test", song.url)
            }

        })
        binding.recyclerViewSongs.adapter = adapter

//        getSong { list ->
//            adapter.submitList(list)
//        }

        viewModel.getSongArtistInfo(channelId)
        viewModel.songArtistInfoList.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    private fun getSong(callback: (List<Song>) -> Unit) {
        val list = mutableListOf<Song>()

        getUrl("IDwytT0wFRM") { url ->
            list.add(Song(0, "SongABC", "Adele", "R.drawable.ran", url))
            list.add(Song(0, "SongABC", "Adele", "R.drawable.ran", url))
            list.add(Song(0, "SongABC", "Adele", "R.drawable.ran", url))
            list.add(Song(0, "SongABC", "Adele", "R.drawable.ran", url))
            list.add(Song(0, "SongABC", "Adele", "R.drawable.ran", url))
            callback(list)
        }
    }


    private fun formatNumber(number: Long): String {
        return if (number < 1000) {
            number.toString()
        } else if (number < 1_000_000) {
            val formattedNumber = number / 1_000.0
            String.format("%.1fK", formattedNumber)
        } else {
            val formattedNumber = number / 1_000_000.0
            String.format("%.1fM", formattedNumber)
        }
    }

    private fun getUrl(videoId: String, callback: (String) -> Unit) {
        val yt = YTExtractor(con = requireContext(), CACHING = true, LOGGING = true, retryCount = 3)

        CoroutineScope(Dispatchers.Main).launch {
            yt.extract(videoId)
            if (yt.state == State.SUCCESS) {
                val ytFiles = yt.getYTFiles()
                var url = ""
                ytFiles?.forEach { key, value ->
                    url = value.url.toString()
                }
                callback(url)
            } else {
                callback("")
            }
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}