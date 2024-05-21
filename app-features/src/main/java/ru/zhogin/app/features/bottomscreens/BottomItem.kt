package ru.zhogin.app.features.bottomscreens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomItem(val title: String, val icon: ImageVector, val route: String) {
    object RandomInsult : BottomItem("Insult", Icons.Filled.ThumbUp, "insult")
    object Database : BottomItem("Database", Icons.Filled.Info, "database")
    object RandomGif : BottomItem("Gif", Icons.Filled.Face, "gif")

    object Settings: BottomItem("Settings", Icons.Filled.Settings, "settings")
}