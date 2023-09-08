package com.example.android.di.module

import com.example.android.di.scope.FragmentScoped
import com.example.android.ui.GetStartedFragment
import com.example.android.ui.bottom_nav_fragment.ArtistFragment
import com.example.android.ui.bottom_nav_fragment.HomepageFragment
import com.example.android.ui.bottom_nav_fragment.LibraryFragment
import com.example.android.ui.bottom_nav_fragment.ProfileFragment
import com.example.android.ui.login.LoginFragment
import com.example.android.ui.login.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("UNUSED")
internal abstract class AppFragmentModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeGetStartedFragment(): GetStartedFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeLoginFragment(): LoginFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeRegisterFragment(): RegisterFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeHomepageFragment(): HomepageFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeLibraryFragment(): LibraryFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeArtistFragment(): ArtistFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeProfileFragment(): ProfileFragment



    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeArcticsFragment(): ArcticsFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeLyricPageFragment(): LyricPageFragment
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeMusicPageFragment(): MusicPageFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeProfileFragment(): ProfileFragment


}

