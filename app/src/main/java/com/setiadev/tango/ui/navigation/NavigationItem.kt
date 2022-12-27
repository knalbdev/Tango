package com.setiadev.tango.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val icon: ImageVector,
    val title: String,
    val screen: Screen
)
