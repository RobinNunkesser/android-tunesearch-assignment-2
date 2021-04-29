package de.hshl.isd.tunesearchassignment2

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Tracks : Screen("tracks")
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    viewModel: MainViewModel,
    startDestination: String = Screen.Main.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Main.route) {
            MainScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.Tracks.route) {
            TracksScreen(viewModel = viewModel)
        }
    }
}