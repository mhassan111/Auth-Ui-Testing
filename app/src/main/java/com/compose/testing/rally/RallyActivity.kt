package com.compose.testing.rally

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.compose.testing.rally.ui.components.RallyTopAppBar
import com.compose.testing.rally.ui.theme.RallyTheme

class RallyActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RallyApp()
        }
    }
}

@Composable
fun RallyApp() {
    RallyTheme {
        val allScreens = RallyScreen.values().toList()
        var currentScreen by rememberSaveable { mutableStateOf(RallyScreen.Overview) }
        Scaffold(
            topBar = {
                RallyTopAppBar(
                    allScreens = allScreens,
                    onTabSelected = { screen ->
                        currentScreen = screen
                    },
                    currentScreen
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                currentScreen.content(onScreenChange = { screen -> currentScreen = screen })
            }
        }
    }
}
