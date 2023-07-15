package com.example.android.di.module

import com.example.android.di.scope.FragmentScoped
import com.example.android.ui.GetStartedFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("UNUSED")
internal abstract class AppFragmentModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeGetStartedFragment(): GetStartedFragment
}

