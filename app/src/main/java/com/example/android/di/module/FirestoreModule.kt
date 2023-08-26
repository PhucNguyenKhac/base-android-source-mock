package com.example.android.di.module

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Suppress("UNUSED")
class FirestoreModule {
    @Provides
    @Singleton
    fun provideFirestoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}
