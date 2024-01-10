package com.example.tveek.ui.navigation

import com.example.tveek.utils.DETAILS
import com.example.tveek.utils.HOME

sealed class Destinations(val route: String) {
    object Home: Destinations(HOME)
    object Details: Destinations(DETAILS)
}
