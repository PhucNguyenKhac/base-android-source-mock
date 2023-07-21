package com.example.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.example.android.model.Song

class SongAdapter(private val item: List<Song>) :
    RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgTodayHitItem: ImageView = itemView.findViewById(R.id.imgTodayHitItem)
        val tvTodayHitName: TextView = itemView.findViewById(R.id.tvTodayHitName)
        val tvTodayHitArtist: TextView = itemView.findViewById(R.id.tvTodayHitArtist)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = item[position]
        holder.imgTodayHitItem.setImageResource(currentItem.imageSong)
        holder.tvTodayHitName.text = currentItem.nameSong
        holder.tvTodayHitArtist.text = currentItem.artistSongName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_today_hits, parent, false)
        return ViewHolder(itemView)
    }
}