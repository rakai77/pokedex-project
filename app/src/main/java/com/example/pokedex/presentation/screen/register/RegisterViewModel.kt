package com.example.pokedex.presentation.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.BaseResult
import com.example.core.domain.model.User
import com.example.core.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _registerState = MutableStateFlow<RegisterUiState>(RegisterUiState.Init)
    val registerState = _registerState.asStateFlow()

    fun register(user: User) {
        viewModelScope.launch {
            when (val result = authUseCase.register(user)) {
                is BaseResult.Success -> {
                    _registerState.value = RegisterUiState.Success(result.data)
                }
                is BaseResult.Error -> {
                    _registerState.value = RegisterUiState.Error(result.errorMessage ?: "Unknown error")
                }
            }
        }
    }
}