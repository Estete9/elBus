package com.notylines.elbus.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.notylines.elbus.ui.screens.savedResults.SavedResultsScreen
import com.notylines.elbus.ui.screens.login.LoginScreen
import com.notylines.elbus.ui.screens.result.ResultScreen
import com.notylines.elbus.ui.screens.run.RunScreen
import com.notylines.elbus.ui.screens.setup.SetupScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = AppScreens.SetupScreen.name) {

        composable(route = AppScreens.LoginScreen.name) {
            LoginScreen(navController = navController)
        }
        composable(route = AppScreens.SetupScreen.name) {
            SetupScreen(navController = navController)
        }
        composable(route = AppScreens.RunScreen.name) {
            RunScreen(navController = navController)
        }
        composable(route = AppScreens.ResultScreen.name) {
            ResultScreen(navController = navController)
        }
        composable(route = AppScreens.SavedResultsScreen.name) {
            SavedResultsScreen(navController = navController)
        }
    }
}