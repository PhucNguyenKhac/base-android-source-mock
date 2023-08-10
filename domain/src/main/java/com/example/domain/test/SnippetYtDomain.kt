package com.example.domain.test

data class SnippetYtDomain(
    val title: String? = null,
    val description: String? = null,
    val customUrl: String? = null,
    val publishedAt: String? = null,
    val thumbnails: ThumbnailsYtDomain? = null,
    val country: String? = null
)