package com.loc.seekmax.presentation.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.seekmax.domain.model.User
import com.loc.seekmax.domain.usecases.auth.AuthUseCase
import com.loc.seekmax.domain.usecases.profile.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileUiState())
    val state: MutableStateFlow<ProfileUiState> = _state

    init {
//        getUserInfo()
        getUserFromLocal()
    }

    fun getUserInfo() =
        _state.update { it.copy(image = "https://i.pinimg.com/564x/20/0f/50/200f509408e5ae122d1a45d110f2faa2.jpg") }

    fun onChangeFirstName(newValue: String) = _state.update { it.copy(firstname = newValue) }

    fun onChangeLastName(newValue: String) = _state.update { it.copy(lastname = newValue) }

    fun onChangeEmail(newValue: String) = _state.update { it.copy(email = newValue) }

    fun onChangePhone(newValue: String) = _state.update { it.copy(phone = newValue) }

    fun onSaveUserInfo() {
        CoroutineScope(Dispatchers.IO).launch {
            profileUseCase.insertUser(User(
                id = _state.value.id,
                firstname = _state.value.firstname,
                lastname = _state.value.lastname,
                image = _state.value.image,
                phone = _state.value.phone,
                email = _state.value.email,
                password = "123456789"
            ))
        }
    }

    fun getUserFromLocal() {
        CoroutineScope(Dispatchers.IO).launch {
            val user = profileUseCase.getUserData(1)!!
            _state.update { it.copy(
                id = user.id,
                firstname = user.firstname,
                lastname = user.lastname,
                image = user.image,
                phone = user.phone,
                email = user.email
            ) }
        }

    }

}