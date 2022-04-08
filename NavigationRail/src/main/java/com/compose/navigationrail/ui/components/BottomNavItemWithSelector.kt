package com.compose.navigationrail.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RowScope.BottomNavItemWithSelector(
    title: String,
    icon: @Composable () -> Unit,
    label: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
    selectedContentColor: Color,
    unSelectedContentColor: Color
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .testTag(title),
        contentAlignment = Alignment.Center
    ) {
        Row {
            AnimatedVisibility(visible = selected, enter = scaleIn(), exit = scaleOut()) {
                Selector()
            }
        }
        Row(
        ) {
            BottomNavigationItem(
                icon = icon,
                label = label,
                selected = selected, onClick = onClick,
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unSelectedContentColor
            )
        }
    }
}

private val DEFAULT_SIZE = 50.dp
private val SelectorShape = RoundedCornerShape(percent = 15)

@Composable
fun Selector(size: Dp = DEFAULT_SIZE, color: Color = MaterialTheme.colors.secondary) {
    Spacer(
        modifier = Modifier
            .size(size)
            .background(color, SelectorShape)
    )
}