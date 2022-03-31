package com.compose.testing.rally.ui.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.compose.testing.rally.RallyScreen
import com.compose.testing.rally.ui.theme.RallyTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RallyTopAppBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun current_tab_selected() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTheme {
                RallyTopAppBar(
                    allScreens = allScreens,
                    onTabSelected = {},
                    currentScreen = RallyScreen.Accounts
                )
            }
        }

        composeTestRule.onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertIsSelected()
    }

    @Test
    fun tab_with_current_label_exists() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTheme {
                RallyTopAppBar(
                    allScreens = allScreens,
                    onTabSelected = {},
                    currentScreen = RallyScreen.Accounts
                )
            }
        }

        composeTestRule.onNode(
            hasText(RallyScreen.Accounts.name.uppercase()) and
                    hasParent(
                        hasContentDescription(RallyScreen.Accounts.name)
                    ),
            useUnmergedTree = true
        ).assertExists()
    }

}