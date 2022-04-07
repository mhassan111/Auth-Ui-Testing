package com.compose.testing.installedApps

import android.graphics.drawable.Drawable

data class InstalledApp(
    val appName : String,
    val packageName : String,
    val installTime : String,
    val versionName : String,
    val icon : Drawable,
    val isSystemApp : Boolean
)
