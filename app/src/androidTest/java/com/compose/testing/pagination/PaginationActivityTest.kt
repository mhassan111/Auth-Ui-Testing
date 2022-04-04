package com.compose.testing.pagination

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.testing.ui.theme.ComposeTestingTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PaginationActivityTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<PaginationActivity>()
    lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        composeRule.setContent {
            ComposeTestingTheme {
                mainViewModel = viewModel<MainViewModel>()
                PaginatedList(mainViewModel)
            }
        }
    }

    @Test
    fun test_initially_loading_state() {
        composeRule.onNodeWithContentDescription("paginated_list").assertExists()
        composeRule.onNodeWithContentDescription("paginated_list").assertIsDisplayed()

        runBlocking {
            delay(2000)
        }

        composeRule.onNodeWithContentDescription("paginated_list").onChildAt(0).assertExists()
        composeRule.onNodeWithContentDescription("paginated_list").onChildAt(0)
            .assertTextContains("Item 1")
    }

    @Test
    fun scroll_and_load_next_page() {
        composeRule.onNodeWithContentDescription("paginated_list").assertExists()
        composeRule.onNodeWithContentDescription("paginated_list").assertIsDisplayed()

        runBlocking {
            delay(2000)
        }

        composeRule.onNodeWithContentDescription("paginated_list").performScrollToIndex(5)
        composeRule.onNodeWithContentDescription("paginated_list").performScrollToIndex(12)
        composeRule.onNodeWithContentDescription("paginated_list").performScrollToIndex(18)
        composeRule.onNodeWithContentDescription("paginated_list").performScrollToIndex(20)

        val loader = composeRule.onNodeWithContentDescription("loader")
        loader.assertIsDisplayed()
    }

}
