package com.example.android.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import com.example.android.R
import com.example.android.model.MostPlayedSong

class MostPlayedSongAdapter(private val mostPlayedSongList: List<MostPlayedSong>) :
    RecyclerView.Adapter<MostPlayedSongAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.most_played_song,
            parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mostPlayedSongList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = mostPlayedSongList[position]
        holder.imageMostPlayedSong.setImageResource(currentItem.imageMostPlayedSong)
        holder.nameMostPlayedSong.text = currentItem.nameMostPlayedSong
        holder.artistMostPlayedSong.text = currentItem.artistMostPlayedSong
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val imageMostPlayedSong : ImageView = itemView.findViewById(R.id.image_mps)
        val nameMostPlayedSong : TextView = itemView.findViewById(R.id.name_mps)
        val artistMostPlayedSong : TextView = itemView.findViewById(R.id.artistMostPlayedSong)
    }
}