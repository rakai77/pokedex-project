package com.example.pokedex.presentation.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.BaseResult
import com.example.core.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Init)
    val loginUiState = _loginUiState.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            when (val result = authUseCase.login(username, password)) {
                is BaseResult.Success -> {
                    _loginUiState.value = LoginUiState.Success(result.data)
                }
                is BaseResult.Error -> {
                    _loginUiState.value = LoginUiState.Error(result.errorMessage ?: "Unknown error")
                }
            }
        }
    }
}