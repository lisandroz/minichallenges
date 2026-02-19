package com.lizardstudio.connectivitychallenge.di

import android.content.Context
import com.lizardstudio.connectivitychallenge.connectivity.AndroidConnectivityObserver
import com.lizardstudio.connectivitychallenge.connectivity.ConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ConnectivityModule {

    @Provides
    fun provideConnectivityObserver(
        @ApplicationContext context: Context
    ): ConnectivityObserver {
        return AndroidConnectivityObserver(
            context = context
        )
    }
}