package com.example.domain.channel

data class SearchChannelResponseDomain(
    val items: List<Items>? = null
) {
    data class Items(
        val snippet: SnippetYtDomain? = null,
    )
}
