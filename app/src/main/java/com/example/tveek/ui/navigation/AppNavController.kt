package com.example.tveek.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tveek.ui.screens.HomeScreen
import com.example.tveek.ui.screens.ShowDetailsScreen
import com.example.tveek.viewmodels.FavouriteTvShowViewModel
import com.example.tveek.viewmodels.TvShowsViewModels
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavController(
    tvShowsViewModel: TvShowsViewModels = koinViewModel(),
    favouriteTvShowViewModel: FavouriteTvShowViewModel = koinViewModel()
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.Home.route,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        }
    ) {
        composable(route = Destinations.Home.route) {
            HomeScreen(tvShowsViewModel,favouriteTvShowViewModel) {
                navController.navigate(Destinations.Details.route)
            }
        }
        composable(route = Destinations.Details.route) {
            ShowDetailsScreen(tvShowsViewModel)
        }
    }
}