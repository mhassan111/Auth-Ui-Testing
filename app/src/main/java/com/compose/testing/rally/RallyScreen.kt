package com.compose.testing.rally

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.compose.testing.rally.ui.overview.OverviewBody

enum class RallyScreen(
    val icon: ImageVector,
    private val body: @Composable ((RallyScreen) -> Unit) -> Unit
) {

    Overview(
        icon = Icons.Filled.PieChart,
        body = { onScreenChange -> OverviewBody(onScreenChange) }
    ),

    Accounts(
        icon = Icons.Filled.AttachMoney,
        body = {}
    ),

    Bills(
        icon = Icons.Filled.MoneyOff,
        body = {}
    );

    @Composable
    fun content(onScreenChange: (RallyScreen) -> Unit) {
        body(onScreenChange)
    }
}