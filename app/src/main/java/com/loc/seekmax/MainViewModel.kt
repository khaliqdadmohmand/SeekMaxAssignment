package com.loc.seekmax

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.loc.seekmax.domain.usecases.app_entry.AppEntryUseCases
import com.loc.seekmax.domain.usecases.auth.AuthUseCase
import com.loc.seekmax.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases,
    private val authUseCase: AuthUseCase
): ViewModel() {

    private val _splashCondition = mutableStateOf(true)
    val splashCondition: State<Boolean> = _splashCondition

    private val _startDestination = mutableStateOf(Route.AppStartNavigation.route)
    val startDestination: State<String> = _startDestination


    private val _isAuthenticated = mutableStateOf(true)
    val isAuthenticated: State<Boolean> = _isAuthenticated


    init {
        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            Log.d("fromshouldstart", shouldStartFromHomeScreen.toString())
            if(shouldStartFromHomeScreen){

                    if(checkAuth()){
                        _startDestination.value = Route.JobsNavigation.route
                    }else{
                        _startDestination.value = Route.LoginNavigation.route
                    }

            }else{
                _startDestination.value = Route.AppStartNavigation.route
            }
            delay(300) //Without this delay, the onBoarding screen will show for a momentum.
            _splashCondition.value = false
        }.launchIn(viewModelScope)
    }

    fun checkAuth(): Boolean{
        authUseCase.readAuthState().onEach { au ->
            Log.d("fromCheckAuth", au.toString())
            _isAuthenticated.value = au


            delay(300)
        }.launchIn(viewModelScope)

        return _isAuthenticated.value
    }
}
