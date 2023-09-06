package com.example.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.android.R
import com.example.android.databinding.ItemSongsBinding
import com.example.android.model.Song


class TopSongOfArtistAdapter :
    ListAdapter<Song, TopSongOfArtistAdapter.ViewHolder>(SongDiffCallback()) {

    inner class ViewHolder(private val binding: ItemSongsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song) {
            binding.songName.text = song.nameSong
            Glide.with(binding.root)
                .load(song.imageSong)
                .error(R.drawable.image_not_available)
                .into(binding.albumImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSongsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TopSongOfArtistAdapter.ViewHolder, position: Int) {
        val song = getItem(position)
        holder.bind(song)
    }

    class SongDiffCallback : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.nameSong == newItem.nameSong
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }
    }

}