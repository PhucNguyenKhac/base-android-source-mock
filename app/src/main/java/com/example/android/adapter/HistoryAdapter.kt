package com.example.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.example.android.model.Song

class HistoryAdapter(private var item: List<Song>): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgSong: ImageView = itemView.findViewById(R.id.img_song)
        val tvSong: TextView = itemView.findViewById(R.id.tv_song)
        val tvSinger: TextView = itemView.findViewById(R.id.tv_singer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.initial_song_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        val currentitem = item[position]
        holder.imgSong.setImageResource(currentitem.imageSong)
        holder.tvSong.text = currentitem.nameSong
        holder.tvSinger.text = currentitem.artistSongName
        holder.itemView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.context,R.anim.rcv_animation))
    }

    override fun getItemCount(): Int {
        return item.size
    }
    fun setData(newData: List<Song>){
        item = newData
        notifyDataSetChanged()
    }
}