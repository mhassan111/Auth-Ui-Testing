package com.compose.imageoffset

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.compose.imageoffset.ui.ImageOffsetApp
import com.compose.imageoffset.ui.ImageOffsetKey
import com.compose.imageoffset.ui.theme.ComposeTestingTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ImageOffsetActivityTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ImageOffsetActivity>()

    @Before
    fun setUp(){
        composeRule.setContent {
            ComposeTestingTheme {
                ImageOffsetApp()
            }
        }
    }

    @Test
    fun test_top_bar_is_displayed() {
        val topBar = composeRule.onNodeWithTag(
            composeRule.activity.getString(R.string.top_bar)
        )
        topBar.assertIsDisplayed()
    }

    @Test
    fun test_image_is_draggable() {
        val image = composeRule.onNodeWithContentDescription(
            composeRule.activity.getString(R.string.map_image)
        )

        // checks if image is displayed
        image.assertIsDisplayed()
        // perform a swipe
        image.performTouchInput {
            swipeLeft()
        }

        val defaultImageOffset = Offset.Zero
        // checks if images is swiped, if offsets don't match
        image.assertImageOffsetNotEquals(defaultImageOffset)
    }
}

fun SemanticsNodeInteraction.assertImageOffsetNotEquals(offset: Offset) =
    assert(!SemanticsMatcher.expectValue(ImageOffsetKey, offset))