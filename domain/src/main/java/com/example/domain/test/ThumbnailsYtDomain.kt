package com.example.domain.test

data class ThumbnailsYtDomain(
    val high: High? = null
) {
    data class High(
        val url: String? = null
    )
}