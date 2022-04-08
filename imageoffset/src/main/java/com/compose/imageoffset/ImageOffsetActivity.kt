package com.compose.imageoffset

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.compose.imageoffset.ui.ImageOffsetApp
import com.compose.imageoffset.ui.theme.ComposeTestingTheme

class ImageOffsetActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeTestingTheme {
                ImageOffsetApp()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    ImageOffsetApp()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTestingTheme {
        Greeting("Android")
    }
}