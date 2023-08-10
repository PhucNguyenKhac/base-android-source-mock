package com.example.domain.test

data class BrandingYtDomain(
    val image: ImageBanner? = null
) {
    data class ImageBanner(
        val bannerUrl: String? = null
    )
}