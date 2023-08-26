package com.example.android.di.module

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
@Suppress("UNUSED")
class AppConstantModule {

    @Provides
    @Named("snippet")
    fun provideSnippetConstant(): String = "snippet"

    @Provides
    @Named("apiKey")
    fun provideApiKeyConstant(): String = "AIzaSyBV7RM1z-3NeAxn8-jZktdYqLT5TqrbNXI"

    @Provides
    @Named("channelType")
    fun provideChannelTypeConstant(): String = "channel"

    @Provides
    @Named("defaultItemsPerPage")
    fun provideDefaultItemsPerPageConstant(): Int = 1

    @Provides
    @Named("order")
    fun provideOrderConstant(): String = "relevance"
}
