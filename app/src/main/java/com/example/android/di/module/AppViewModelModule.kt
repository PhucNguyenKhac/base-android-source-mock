package com.example.android.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.di.ViewModelFactory
import com.example.android.di.ViewModelKey
import com.example.android.viewmodel.WellnessViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
abstract class AppViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WellnessViewModel::class)
    abstract fun bindWellnessViewModel(wellnessViewModel: WellnessViewModel): ViewModel
}
