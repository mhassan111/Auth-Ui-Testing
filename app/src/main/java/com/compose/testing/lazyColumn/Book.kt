package com.compose.testing.lazyColumn


data class Book(
    val id: String,
    val name: String
)

fun getBooks() : List<Book> {
    val testBooks = mutableListOf<Book>()
    for (i in 1..100) {
        testBooks.add(Book(i.toString(), "Book $i"))
    }
    return testBooks
}