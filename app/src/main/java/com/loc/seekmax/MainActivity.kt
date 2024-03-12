package com.loc.seekmax

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.loc.seekmax.data.local.JobsDao
import com.loc.seekmax.domain.model.User
import com.loc.seekmax.domain.usecases.app_entry.AppEntryUseCases
import com.loc.seekmax.domain.usecases.auth.AuthUseCase

import com.loc.seekmax.presentation.navgraph.NavGraph
import com.loc.seekmax.presentation.navgraph.Route
import com.loc.seekmax.ui.theme.SeekAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

//    @Inject
//    lateinit var dao: JobsDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

//            if(viewModel.isAuthenticated.value){
//                lifecycleScope.launch {
//                    dao.insertUser(
//                        User(
//                            id = 1,
//                            firstname = "khaliq",
//                            password = "123456789",
//                            lastname = "dad",
//                            email = "abc@gmail.com",
//                            phone = "0123456789",
//                            image = "https://www.gstatic.com/webp/gallery/1.jpg"
//                        )
//                    )
//                }
//            }



        installSplashScreen().apply {
            setKeepOnScreenCondition(condition = { viewModel.splashCondition.value })
        }
        setContent {
            SeekAppTheme {
                val isSystemInDarkMode = isSystemInDarkTheme()
                val systemUiColor = rememberSystemUiController()
                SideEffect {
                    systemUiColor.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemInDarkMode
                    )
                }
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxSize()
                ) {
                    NavGraph(startDestination = viewModel.startDestination.value)
                }
            }
            }
        }
}
