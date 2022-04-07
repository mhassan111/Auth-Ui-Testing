package com.compose.testing.installedApps.repository

import android.content.Context
import com.compose.testing.installedApps.InstalledApp
import com.compose.testing.installedApps.InstalledAppBlockUtils

class InstalledAppRepositoryImpl(private val context: Context) : InstalledAppRepository {

    override
    suspend fun getInstalledApps(): List<InstalledApp> {
        return InstalledAppBlockUtils.getInstalledApps(context = context)
    }
}