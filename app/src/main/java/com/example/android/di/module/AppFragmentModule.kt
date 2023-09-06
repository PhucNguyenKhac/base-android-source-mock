package com.example.android.di.module

import com.example.android.di.scope.FragmentScoped
import com.example.android.ui.fragment.bottom_nav.ArtistInfoFragment
import com.example.android.ui.fragment.bottom_nav.ArtistFragment
import com.example.android.ui.fragment.bottom_nav.HomepageFragment
import com.example.android.ui.fragment.bottom_nav.LibraryFragment
import com.example.android.ui.fragment.login.*
import com.example.android.ui.fragment.playing_music.LyricPageFragment
import com.example.android.ui.fragment.playing_music.MusicPageFragment
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
    internal abstract fun contributeSignUpFragment(): SignUpFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeContinueFragment(): ContinueFragment

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
    internal abstract fun contributeArcticsFragment(): ArtistInfoFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeLyricPageFragment(): LyricPageFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeMusicPageFragment(): MusicPageFragment
}

