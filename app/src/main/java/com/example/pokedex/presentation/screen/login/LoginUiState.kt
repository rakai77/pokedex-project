package com.example.pokedex.presentation.screen.login

import com.example.core.domain.model.User

sealed class LoginUiState {
    data object Init : LoginUiState()
    data class Success(val data: User) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}