package com.compose.testing.canvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.compose.testing.canvas.components.InstagramIcon
import com.compose.testing.ui.theme.ComposeTestingTheme

class AnimatedCircleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeTestingTheme {
//                AnimatedCircle(
//                    proportions = UserData.items.extractProportions { it.amount },
//                    colors = UserData.items.map { it.color }
//                )
//                MyCanvas()
                SocialMediaIcons()
//                CircularIndicator()
//                LoadingAnimation()
            }
        }
    }
}

@Composable
fun MyCanvas() {
//    Box(modifier = Modifier
//        .fillMaxSize()
//        .drawBehind {
//
//        })

    Canvas(
        modifier = Modifier
            .padding(20.dp)
            .size(300.dp)
    ) {
        drawRect(
            color = Color.Black,
            size = size
        )

        drawRect(
            color = Color.Black,
            size = size
        )

        drawRect(
            color = Color.Red,
            size = Size(100f, 100f),
            style = Stroke(
                width = 3.dp.toPx()
            ),
            topLeft = Offset(100f, 100f)
        )

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color.Red, Color.Yellow),
                center = Offset.Unspecified,
                radius = 100f,
            ),
            radius = 100f
        )

        drawArc(
            color = Color.Green,
            startAngle = 0f,
            sweepAngle = 270f,
            useCenter = true,
            topLeft = Offset(200f, 100f),
            size = Size(200f, 200f),
        )

        drawArc(
            color = Color.Cyan,
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(200f, 250f),
            size = Size(200f, 200f)
        )

//        drawArc(
//            color = Color.Green,
//            startAngle = 0f,
//            sweepAngle = 270f,
//            useCenter = true,
//            topLeft = Offset(100f, 100f),
//            size = Size(200f, 200f),
//            style = Stroke(
//                width = 3.dp.toPx()
//            ),
//        )
        drawOval(
            color = Color.Magenta,
            topLeft = Offset(500f, 100f),
            size = Size(200f, 300f)
        )
        drawLine(
            color = Color.Cyan,
            start = Offset(300f, 700f),
            end = Offset(700f, 700f),
            strokeWidth = 5.dp.toPx()
        )
    }

}