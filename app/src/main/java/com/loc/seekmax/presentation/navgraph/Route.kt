package com.loc.seekmax.presentation.navgraph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object OnBoardingScreen : Route(route = "onBoardingScreen")

    object LoginNavigation: Route(route = "loginNavigation")
    object LoginScreen: Route(route = "loginScreen")

    object HomeScreen : Route(route = "homeScreen")
    object HomeScreenNavigation : Route(route = "HomeScreenNavigation")

    object SearchScreen : Route(route = "searchScreen")

    object BookmarkScreen : Route(route = "bookMarkScreen")

    object DetailsScreen : Route(route = "detailsScreen")

    object ProfileScreen : Route(route = "profileScreen")

    object AppStartNavigation : Route(route = "appStartNavigation")

    object JobsNavigation : Route(route = "newsNavigation")

    object JobsNavigatorScreen : Route(route = "newsNavigator")
}

