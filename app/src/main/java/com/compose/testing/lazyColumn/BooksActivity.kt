package com.compose.testing.lazyColumn

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.testing.ui.theme.ComposeTestingTheme

class LazyColumnActivity : ComponentActivity() {

    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestingTheme {
//                val booksList = mutableStateListOf<Book>()
//                booksList.addAll(getBooks())
//                BooksList(books = booksList)

                val books = mutableListOf<Book>()
                for (i in 1..100) {
                    books.add(Book(i.toString(), "Book $i"))
                }
                BooksList(books = books)
            }
        }
    }
}


@Composable
fun BooksList(books: List<Book>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("books_list"),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        items(books) { book ->
            Column {
                Text(text = book.id, fontWeight = FontWeight.Light, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = book.name, fontWeight = FontWeight.Bold, fontSize = 25.sp)
            }
        }
    }
}

//@Composable
//fun BooksList(books: SnapshotStateList<Book>) {
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .testTag("books_list"),
//        contentPadding = PaddingValues(20.dp),
//        verticalArrangement = Arrangement.spacedBy(25.dp)
//    ) {
//        items(books) { book ->
//            Column {
//                Text(text = book.id, fontWeight = FontWeight.Light, fontSize = 20.sp)
//                Spacer(modifier = Modifier.height(5.dp))
//                Text(text = book.name, fontWeight = FontWeight.Bold, fontSize = 25.sp)
//            }
//        }
//    }
//}

