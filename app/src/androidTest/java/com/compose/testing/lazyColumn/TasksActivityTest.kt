package com.compose.testing.lazyColumn

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.compose.testing.ui.theme.ComposeTestingTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TasksActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<TasksActivity>()

    @Test
    fun testTaskListScreenIfTasksAreEmpty() {
        composeTestRule.setContent {
            ComposeTestingTheme {
                TaskListScreen(emptyList())
            }
        }
        composeTestRule.onNodeWithTag(TASK_LIST_TEST_TAG)
            .onChildren()
            .assertCountEquals(0)
    }

    @Test
    fun testCountOfListItemsAreEqual() {
        composeTestRule.setContent {
            ComposeTestingTheme {
                TaskListScreen(getTasksList())
            }
        }
        composeTestRule.onNodeWithTag(TASK_LIST_TEST_TAG)
            .onChildren()
            .assertCountEquals(14)
    }

    @Test
    fun testFirstAndLastTaskDescription() {
        composeTestRule.setContent {
            ComposeTestingTheme {
                TaskListScreen(getTasksList())
            }
        }

        composeTestRule.apply {
            onNodeWithTag(TASK_LIST_TEST_TAG)
                .onChildren()
                .onFirst()
                .assert(hasText("Buy Milk and eggs!"))

            onNodeWithTag(TASK_LIST_TEST_TAG)
                .onChildren()
                .onLast()
                .assert(hasText("Plan for weekend trip"))
        }
    }

    fun getTasksList(): List<Task> = listOf(
        Task("Buy Milk and eggs!"),
        Task("Run 3 miles"),
        Task("Take puppy to clinic"),
        Task("Plan for weekend trip"),
        Task("Plan for weekend trip"),
        Task("Plan for weekend trip"),
        Task("Plan for weekend trip"),
        Task("Plan for weekend trip"),
        Task("Plan for weekend trip"),
        Task("Plan for weekend trip"),
        Task("Plan for weekend trip"),
        Task("Plan for weekend trip"),
        Task("Plan for weekend trip"),
        Task("Plan for weekend trip")
    )
}