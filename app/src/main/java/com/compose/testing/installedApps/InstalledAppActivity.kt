package com.compose.testing.installedApps

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.compose.testing.ui.theme.ComposeTestingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InstalledAppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestingTheme {
                InstalledAppsList()
            }
        }
    }
}

@Composable
fun InstalledAppsList(
    viewModel: InstalledAppViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val state = viewModel.state.value

    LaunchedEffect(key1 = true) {
        viewModel.loadInstalledApps()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyColumn() {
            items(state.installedApps) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .semantics {
                            contentDescription = "installedAppItem"
                        },
                ) {
                    Image(
                        modifier = Modifier.wrapContentWidth(),
                        painter = rememberAsyncImagePainter(item.icon),
                        contentDescription = "appIcon"
                    )
                    Column() {
                        Text(text = item.appName, fontSize = 25.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = item.versionName, fontSize = 18.sp)
                    }
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.semantics {
                    contentDescription = "loader"
                })
        }
    }

}