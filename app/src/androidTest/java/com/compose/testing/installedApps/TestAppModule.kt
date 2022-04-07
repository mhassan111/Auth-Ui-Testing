package com.compose.testing.installedApps

import android.content.Context
import com.compose.testing.installedApps.repository.InstalledAppRepository
import com.compose.testing.installedApps.repository.InstalledAppRepositoryImpl
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