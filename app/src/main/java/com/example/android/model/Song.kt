package com.example.android.model

import java.io.Serializable

data class Song(
    val imageSong: String,
    val nameSong: String,
    val artistSongName: String
) : Serializable
