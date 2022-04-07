package com.compose.testing.installedApps.repository

import com.compose.testing.installedApps.InstalledApp
import kotlinx.coroutines.flow.Flow

interface InstalledAppRepository {
    suspend fun getInstalledApps(): List<InstalledApp>
}