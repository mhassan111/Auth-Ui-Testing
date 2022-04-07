package com.compose.testing.installedApps

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object InstalledAppBlockUtils {

    fun getInstalledApps(context: Context): List<InstalledApp> {
        val installedAppList: MutableList<InstalledApp> = mutableListOf()
        try {
            val packageManager = context.packageManager
            val packages = packageManager.getInstalledPackages(0)
            for (pkgInfo in packages) {
                val packageName = pkgInfo.packageName
                val versionName = pkgInfo.versionName
                val packageInfo = packageManager.getPackageInfo(
                    packageName,
                    PackageManager.GET_META_DATA or PackageManager.GET_PROVIDERS
                ) ?: continue
                val appInfo = packageInfo.applicationInfo
                try {
                    val applicationLabel =
                        packageManager.getApplicationLabel(appInfo).toString()
                    val lastModified = File(appInfo.sourceDir).lastModified()
                    val icon = findAppIcon(context, packageName)
//                        val iconBase64 = BitmapToBase64Converter().imageToBase64(icon.toBitmap())

                    val installedApp = InstalledApp(
                        appName = applicationLabel,
                        packageName = packageName,
                        installTime = formatDate(lastModified.toString()),
                        versionName = versionName,
                        icon = icon,
                        isSystemApp = isSystemApp(appInfo) == 1
                    )
                    installedAppList.add(installedApp)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return installedAppList
    }

    private fun findAppIcon(context: Context, packageName: String): Drawable {
        return context.packageManager.getApplicationIcon(packageName)
    }

    private fun isSystemApp(pkgInfo: ApplicationInfo): Int {
        return if (pkgInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0) 1 else 0
    }

    fun imageToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val b = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    fun base64ToImage(encodedString: String?): Bitmap? {
        return try {
            val encodeByte = Base64.decode(encodedString, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
            bitmap
        } catch (e: Exception) {
            null
        }
    }

    fun formatDate(date: String?): String {
        if (date == null) {
            return ""
        }
        var timestamp = java.lang.Long.valueOf(date)
        if (date.length < 13) {
            timestamp *= 1000
        }
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(timestamp))
    }
}
