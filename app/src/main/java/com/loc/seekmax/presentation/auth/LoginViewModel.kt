package com.loc.seekmax.presentation.auth

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.loc.seekmax.data.manger.LocalUserMangerImpl
import com.loc.seekmax.domain.model.User
import com.loc.seekmax.domain.repository.JobsRepository
import com.loc.seekmax.domain.usecases.auth.AuthUseCase
import com.loc.seekmax.domain.usecases.profile.ProfileUseCase
import com.loc.seekmax.presentation.bookmark.BookmarkState
import com.loc.seekmax.presentation.onboarding.OnBoardingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel  @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val repository: JobsRepository
): ViewModel() {

    lateinit var user: User


    suspend fun loginUser() {
//       user = authUseCase.authenticate("khaliq","123456789")

        Log.d("Userdetail",authUseCase.authenticate("khaliq","123456789").image)

    }

    suspend fun getLogin(name: String) {
        Log.d("detaillllll===",repository.login("khaliq", "123456789").image)
    }


    fun onEvent(event: AuthEvent){
        when(event){
            is AuthEvent.SaveAuthState ->{
                saveAuthEntry()
            }
            is AuthEvent.ClearAuthState ->{
                clearAuthEntry()
            }
            is AuthEvent.SaveUserInfor -> {
                saveUserInfo()
            }
        }
    }

    private fun saveAuthEntry() {
        viewModelScope.launch {
            authUseCase.saveAuthState()
        }
    }

    private fun clearAuthEntry() {
        viewModelScope.launch {
            authUseCase.clearState()
        }
    }

    private fun saveUserInfo() {
        viewModelScope.launch {

            authUseCase.upsertUser(User(
                id = 1,
                firstname = "khaliq",
                password = "123456789",
                lastname = "dad",
                email = "abc@gmail.com",
                phone = "0123456789",
                image = "https://www.gstatic.com/webp/gallery/1.jpg"
            ))
        }
    }

}
