package com.ganesh.spendwise.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavDestination(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    object Home : BottomNavDestination(
        route = AppDestination.Dashboard.route,
        title = "Home",
        icon = Icons.Default.Home
    )

    object Statistics : BottomNavDestination(
        route = AppDestination.Statistics.route,
        title = "Stats",
        icon = Icons.Default.BarChart
    )
}