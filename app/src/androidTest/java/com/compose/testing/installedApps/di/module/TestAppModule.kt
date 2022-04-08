package com.compose.testing.installedApps.di.module

import android.content.Context
import com.compose.testing.installedApps.di.repository.FakeInstalledAppRepository
import com.compose.testing.installedApps.repository.InstalledAppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideInstalledAppRepository(@ApplicationContext context: Context): InstalledAppRepository {
        return FakeInstalledAppRepository(context)
    }

}