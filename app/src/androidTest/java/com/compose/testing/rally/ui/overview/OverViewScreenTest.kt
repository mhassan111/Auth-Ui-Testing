package com.compose.testing.rally.ui.overview

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class OverViewScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun alert_section_displayed() {
        val alertCard = composeRule.onNodeWithTag("alertCard")
        alertCard.assertExists()
        alertCard.assertIsDisplayed()
    }
}