package com.compose.testing.canvas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.compose.testing.canvas.components.CustomComponent
import com.compose.testing.ui.theme.ComposeTestingTheme

@Composable
fun CircularIndicator() {

    ComposeTestingTheme {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var value by remember { mutableStateOf(0) }
            CustomComponent(indicatorValue = value)

            TextField(
                value = value.toString(), onValueChange = {
                    value = if (it.isNotEmpty()) {
                        it.toInt()
                    } else {
                        0
                    }
                }, keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
        }

    }

}