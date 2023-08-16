package com.example.domain.test

data class ChannelResponseDomain(
    val items: List<Items>? = null
) {
    data class Items(
        val id: String? = null,
        val snippet: SnippetYtDomain? = null,
        val branding: BrandingYtDomain? = null
    )
}
