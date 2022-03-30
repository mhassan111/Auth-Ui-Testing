package com.compose.testing.counter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.compose.testing.R

@Composable
fun Counter() {

    var counter by remember {
        mutableStateOf(0)
    }

    Column {
        Button(
            modifier = Modifier.padding(16.dp),
            onClick = { counter++ }
        ) {
            Text(text = stringResource(id = R.string.increment_counter))
        }
        Text(text = stringResource(id = R.string.clicks, counter))
    }
}
