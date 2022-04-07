package com.compose.testing.installedApps

import android.content.Context
import androidx.core.content.ContextCompat
import com.compose.testing.R
import com.compose.testing.installedApps.repository.InstalledAppRepository

class FakeInstalledAppRepository(private val context: Context) : InstalledAppRepository {

    private val installedApps = mutableListOf<InstalledApp>()

    override suspend fun getInstalledApps(): List<InstalledApp> {
        for (i in 1..100) {
            installedApps.add(
                InstalledApp(
                    "appName $i",
                    "packageName $i",
                    InstalledAppBlockUtils.formatDate(System.currentTimeMillis().toString()),
                    "ver ${i + 1}",
                    ContextCompat.getDrawable(context, R.mipmap.ic_launcher)!!,
                    false
                )
            )
        }
        return installedApps.toList()
    }
}