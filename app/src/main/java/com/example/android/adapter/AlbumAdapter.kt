package com.example.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.example.android.model.Album

class AlbumAdapter(private val albumList: List<Album>) :
    RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val albumImage : ImageView = itemView.findViewById(R.id.album_image)
        val albumTitle : TextView = itemView.findViewById(R.id.album_name)
        val yearRelease: TextView = itemView.findViewById(R.id.year_release)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_album_list, parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = albumList[position]
        holder.albumImage.setImageResource(currentItem.imgAlbum)
        holder.albumTitle.text = currentItem.albumName
        holder.yearRelease.text = currentItem.yearReleased.toString()

    }


}