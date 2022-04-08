package com.compose.testing.installedApps

import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import com.compose.testing.installedApps.di.module.AppModule
import com.compose.testing.ui.theme.ComposeTestingTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class InstalledAppActivityTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<InstalledAppActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            ComposeTestingTheme {
                InstalledAppsList()
            }
        }
    }

    @Test
    fun test_first_installed_app_displayed() {
        composeRule.onAllNodesWithTag("installedAppItem")
            .assertAny(hasText("appName 1"))
    }
}