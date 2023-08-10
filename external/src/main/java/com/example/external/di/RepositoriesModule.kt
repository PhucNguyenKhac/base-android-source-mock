package com.example.external.di

import com.example.domain.test.ChannelRepository
import com.example.domain.usecases.LoginRepository
import com.example.external.provider.LoginProvider
import com.example.external.test.ChannelProvider
import dagger.Binds
import dagger.Module

@Module
@Suppress("UNUSED")
abstract class RepositoriesModule {
    @Binds
    abstract fun bindLoginRepository(loginProvider: LoginProvider): LoginRepository

    @Binds
    abstract fun bindChannelRepository(channelProvider: ChannelProvider): ChannelRepository
}
