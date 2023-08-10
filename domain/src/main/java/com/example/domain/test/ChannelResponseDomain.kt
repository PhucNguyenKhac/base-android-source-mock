package com.example.domain.test

import com.google.gson.annotations.SerializedName

data class ChannelResponseDomain(
    val items: List<Items>? = null
) {
    data class Items(
        val id: String? = null,
        val snippet: SnippetYtDomain? = null,
        val branding: BrandingYtDomain? = null
    )
}
