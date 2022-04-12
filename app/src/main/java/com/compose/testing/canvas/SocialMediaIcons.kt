package com.compose.testing.canvas

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.compose.testing.canvas.components.*

@Composable
fun SocialMediaIcons() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InstagramIcon()
        Spacer(modifier = Modifier.height(20.dp))
        FacebookIcon()
        Spacer(modifier = Modifier.height(20.dp))
        MessengerIcon()
        Spacer(modifier = Modifier.height(20.dp))
        GooglePhotosIcon()
        Spacer(modifier = Modifier.height(20.dp))
        WeatherAppIcon()
    }
}