package com.example.external.channel

import com.example.domain.channel.ThumbnailsYtDomain
import com.google.gson.annotations.SerializedName

data class ThumbnailsYt(
    @SerializedName("high")
    val high: High
) {
    data class High(
        @SerializedName("url")
        val url: String
    )

    fun mapThumbnailsYtToDomain(thumbnailsYt: ThumbnailsYt): ThumbnailsYtDomain {
        return ThumbnailsYtDomain(
            high = thumbnailsYt.high.let { high ->
                ThumbnailsYtDomain.High(
                    url = high.url
                )
            }
        )
    }

}