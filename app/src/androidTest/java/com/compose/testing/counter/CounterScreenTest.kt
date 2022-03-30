package com.compose.testing.counter

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.compose.testing.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CounterScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<CounterActivity>()

    @Test
    fun counter_initially_zero() {
        val text = composeTestRule.activity.getString(R.string.clicks, 0)
        composeTestRule.onNodeWithText(text).assertExists()
    }

    @Test
    fun clickButton_incrementCounter() {
        val incrementCounterText = composeTestRule.activity.getString(R.string.increment_counter)
        composeTestRule.onNodeWithText(incrementCounterText).performClick()
        val text = composeTestRule.activity.getString(R.string.clicks, 1)
        composeTestRule.onNodeWithText(text).assertExists()
    }
}