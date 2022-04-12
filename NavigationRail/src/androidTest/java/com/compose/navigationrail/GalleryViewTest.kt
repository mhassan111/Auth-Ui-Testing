package com.compose.navigationrail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import com.compose.navigationrail.models.DataProvider
import com.compose.navigationrail.ui.NavigationRailApp
import com.compose.navigationrail.ui.components.GalleryView
import com.compose.navigationrail.ui.theme.ComposeTestingTheme
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalFoundationApi::class)
class GalleryViewTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<GalleryActivity>()

    @Test
    fun test_bottom_nav_bar_displayed() {
        composeRule.setContent {
            ComposeTestingTheme {
                NavigationRailApp()
            }
        }

        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.bottom_nav))
            .assertIsDisplayed()
    }

    @Test
    fun test_tabs_are_displayed() {
        composeRule.setContent {
            ComposeTestingTheme {
                NavigationRailApp()
            }
        }

        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.plants))
            .assertIsDisplayed()
        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.plants))
            .performTouchInput {
                click()
            }
        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.plants))
            .assertIsSelected()
        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.birds))
            .assertIsDisplayed()
        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.animals))
            .assertIsDisplayed()
        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.lakes))
            .assertIsDisplayed()
        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.rocks))
            .assertIsDisplayed()
    }

    @Test
    fun test_birds_tab_click() {
        composeRule.setContent {
            ComposeTestingTheme {
                NavigationRailApp()
            }
        }

        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.birds))
            .assertIsDisplayed()
        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.birds))
            .performTouchInput {
                click()
            }

//        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.nav_rail_top_bar_tag))
//            .assert(hasText(composeRule.activity.getString(R.string.birds), ignoreCase = true))
//
        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.nav_rail_top_bar_tag))
            .onChildren()
            .assertAny(hasText(composeRule.activity.getString(R.string.birds), ignoreCase = true))
    }

    @Test
    fun test_gallery_content_vertically_scrollable() {
        composeRule.setContent {
            ComposeTestingTheme {
                var id by rememberSaveable { mutableStateOf(0) }
                GalleryView(
                    galleryList = DataProvider.plantList,
                    currentImageId = id,
                    onImageSelected = { newId -> id = newId },
                    horizontalPadding = 10.dp
                )
            }
        }

        val lastEntry = DataProvider.plantList.last()
        val lastEntryDescription = composeRule.activity.getString(
            R.string.image_description, lastEntry.name, lastEntry.id
        )
        composeRule.onNodeWithContentDescription(lastEntryDescription).assertDoesNotExist()
        composeRule.onNode(SemanticsMatcher.keyIsDefined(SemanticsProperties.VerticalScrollAxisRange))
            .assertExists()
            .performTouchInput { swipeUp() }

        composeRule.onNodeWithContentDescription(lastEntryDescription)
            .performScrollTo()
            .assertIsDisplayed()
    }
}