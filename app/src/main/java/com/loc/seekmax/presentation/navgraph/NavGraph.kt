package com.loc.seekmax.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.loc.seekmax.presentation.auth.LoginScreen
import com.loc.seekmax.presentation.auth.LoginViewModel
import com.loc.seekmax.presentation.home.HomeViewModel
import com.loc.seekmax.presentation.jobs_navigator.JobsNavigator
import com.loc.seekmax.presentation.navgraph.Route
import com.loc.seekmax.presentation.onboarding.*
import kotlinx.coroutines.flow.onEach

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(event = viewModel::onEvent)
            }
        }

        navigation(
            route = Route.LoginNavigation.route,
            startDestination = Route.LoginScreen.route
        ) {
            composable(route = Route.LoginScreen.route) {
                val viewModel: LoginViewModel = hiltViewModel()
                LoginScreen(viewModel = viewModel, event = viewModel::onEvent, navController = navController)
            }
        }

        navigation(
            route = Route.JobsNavigation.route,
            startDestination = Route.JobsNavigatorScreen.route
        ) {
            composable(route = Route.JobsNavigatorScreen.route){
                JobsNavigator()
            }
        }
    }
}