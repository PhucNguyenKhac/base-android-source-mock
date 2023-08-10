package com.example.external.test

import androidx.annotation.Keep
import com.example.domain.test.BrandingYtDomain
import com.example.domain.test.SnippetYtDomain
import com.example.domain.test.ThumbnailsYtDomain
import com.google.gson.annotations.SerializedName

data class BrandingYt(
    @SerializedName("image")
    val image: ImageBanner
) {

    data class ImageBanner(
        @SerializedName("bannerExternalUrl")
        val bannerUrl: String
    )

    fun mapBrandingYtToDomain(branding: BrandingYt): BrandingYtDomain {
        return BrandingYtDomain(
            image = branding.image.let { image ->
                BrandingYtDomain.ImageBanner(
                    bannerUrl = image.bannerUrl
                )
            }
        )
    }
}