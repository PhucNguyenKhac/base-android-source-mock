package com.example.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.android.model.SongInAlbum
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R


class SongInAlbumAdapter (private val songInAlbumList: List<SongInAlbum>):
    RecyclerView.Adapter<SongInAlbumAdapter.ViewHolder>(){

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val imageSong : ImageView = itemView.findViewById(R.id.album_image)
            val nameSong : TextView = itemView.findViewById(R.id.song_name)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_songs,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return songInAlbumList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = songInAlbumList[position]
        holder.imageSong.setImageResource(currentItem.imageSong)
        holder.nameSong.text = currentItem.nameSong
    }

}