package com.example.external.channel

import com.example.domain.channel.SearchChannelResponseDomain
import com.google.gson.annotations.SerializedName

data class SearchChannelEntity(

    @SerializedName("items")
    val items: List<Items>

) {
    data class Items(

        @SerializedName("snippet")
        val snippet: SnippetYt

    )

    fun mapSearchChannelEntityListToDomain(entityList: List<Items>): List<SearchChannelResponseDomain.Items> {
        return entityList.map { entity ->
            SearchChannelResponseDomain.Items(
                snippet = entity.snippet.mapSnippetYtToDomain(entity.snippet)
            )
        }
    }

}