package com.compose.testing.lazyColum

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.compose.testing.lazyColumn.Book
import com.compose.testing.lazyColumn.BooksList
import com.compose.testing.ui.theme.ComposeTestingTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BooksActivityTest {

    @get:Rule
    val composeRule = createComposeRule()
    val testBooks = mutableStateListOf<Book>()
    val books = mutableListOf<Book>()

    @Test
    fun test_books_list_exists_and_displayed() {
        val booksList = composeRule.onNodeWithTag("books_list")
        booksList.assertExists()
        booksList.assertIsDisplayed()
    }

    @Test
    fun check_books_count_is_zero_initially() {
        composeRule.setContent {
            ComposeTestingTheme {
                BooksList(books = emptyList())
            }
        }

        composeRule.onNodeWithTag("books_list")
            .onChildren()
            .assertCountEquals(4)
    }

    private fun setUpBooks() {
        (1..4).forEach {
            books.add(Book(it.toString(), "Book $it"))
        }

        composeRule.setContent {
            ComposeTestingTheme {
                BooksList(books = books)
            }
        }
    }

    @Test
    fun add_books_and_check_list_is_not_empty() {
        (1..4).forEach {
            books.add(Book(it.toString(), "Book $it"))
        }

        composeRule.setContent {
            ComposeTestingTheme {
                BooksList(books = books)
            }
        }

        composeRule.onNodeWithTag("books_list")
            .onChildren()
            .assertCountEquals(4)
    }
}