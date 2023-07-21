package com.example.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.example.android.model.Artist
import com.example.android.model.Song
import java.text.NumberFormat
import java.util.*

class ArtistAdapter (private val item: List<Artist>) :
    RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgArtist: ImageView = itemView.findViewById(R.id.imgArtist)
        val tvArtistName: TextView = itemView.findViewById(R.id.tvArtistName)
        val tvListenersCountNumber: TextView = itemView.findViewById(R.id.tvListenersCountNumber)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = item[position]
        holder.imgArtist.setImageResource(currentItem.imageArtist)
        holder.tvArtistName.text = currentItem.nameArtist

        //formatted number
        val countListenerMonthly = currentItem.countListenerMonthly
        val formattedNumber = NumberFormat.getNumberInstance(Locale("vi, VN")).format(countListenerMonthly)

        holder.tvListenersCountNumber.text = formattedNumber.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_artist, parent, false)
        return ViewHolder(itemView)
    }
}