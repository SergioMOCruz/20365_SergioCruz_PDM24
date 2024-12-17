package com.sergio.store.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergio.store.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val authController: AuthRepository = AuthRepository()) : ViewModel() {

    private val _loginState = MutableStateFlow<Result<Unit>?>(null)
    val loginState: StateFlow<Result<Unit>?> = _loginState

    private val _registerState = MutableStateFlow<Result<Unit>?>(null)
    val registerState: StateFlow<Result<Unit>?> = _registerState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = authController.login(email, password)
            _loginState.value = result
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            val result = authController.register(email, password)
            _registerState.value = result
        }
    }

    fun isLoggedIn(): Boolean = authController.isLoggedIn()

    fun getCurrentUserId(): String? = authController.getCurrentUserId()

    fun resetLoginState() {
        _loginState.value = null
    }

    fun resetRegisterState() {
        _registerState.value = null
    }

    fun logout(): Unit = authController.logout()
}
