package com.example.external.di

import com.example.domain.usecases.LoginRepository
import com.example.external.provider.LoginProvider
import dagger.Binds
import dagger.Module

@Module
@Suppress("UNUSED")
abstract class RepositoriesModule {
    @Binds
    abstract fun bindLoginRepository(loginProvider: LoginProvider): LoginRepository
}
