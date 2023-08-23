package com.example.domain.channel

data class ThumbnailsYtDomain(
    val high: High? = null
) {
    data class High(
        val url: String? = null
    )
}