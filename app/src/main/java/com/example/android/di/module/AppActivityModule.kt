package com.example.android.di.module

import com.example.android.di.scope.ActivityScoped
import com.example.android.ui.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("UNUSED")
abstract class AppActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun bindLoginActivity(): LoginActivity
}
