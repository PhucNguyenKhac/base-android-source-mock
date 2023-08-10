package com.example.external.test


import com.example.domain.test.SnippetYtDomain
import com.google.gson.annotations.SerializedName

data class SnippetYt(
    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("customUrl")
    val customUrl: String,

    @SerializedName("publishedAt")
    val publishedAt: String,

//    @SerializedName("thumbnails")
//    val thumbnails: ThumbnailsYt,

    @SerializedName("country")
    val country: String

) {
    fun mapSnippetYtToDomain(snippet: SnippetYt): SnippetYtDomain {
        return SnippetYtDomain(
            title = snippet.title,
            description = snippet.description,
            customUrl = snippet.customUrl,
            publishedAt = snippet.publishedAt,
//            thumbnails = snippet.thumbnails.mapThumbnailsYtToDomain(snippet.thumbnails),
            country = snippet.country
        )
    }

}