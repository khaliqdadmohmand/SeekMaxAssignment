package com.loc.seekmax.presentation.auth

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.loc.seekmax.domain.model.Article
import com.loc.seekmax.presentation.auth.component.LoginForm
import com.loc.seekmax.presentation.onboarding.OnBoardingEvent

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    event: (AuthEvent) -> Unit,
    navController: NavHostController
) {

    LoginForm(event = event, navController = navController)

}