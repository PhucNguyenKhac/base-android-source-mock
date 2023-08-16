package com.example.external.test

import com.example.domain.test.ChannelResponseDomain
import com.google.gson.annotations.SerializedName

data class ChannelEntity(

    @SerializedName("items")
    val items: List<Items>

) {
    data class Items(

        @SerializedName("id")
        val id: String,

        @SerializedName("snippet")
        val snippet: SnippetYt,

        @SerializedName("branding")
        val branding: BrandingYt
    )

    fun mapChannelEntityListToDomain(entityList: List<Items>): List<ChannelResponseDomain.Items> {
        return entityList.map { entity ->
            ChannelResponseDomain.Items(
                id = entity.id,
                snippet = entity.snippet.mapSnippetYtToDomain(entity.snippet),
//                branding = entity.branding.mapBrandingYtToDomain(entity.branding)
            )
        }
    }

}