package com.example.pokedex.presentation.screen.register

sealed class RegisterUiState {
    data object Init : RegisterUiState()
    data class Success(val data: Boolean) : RegisterUiState()
    data class Error(val message: String) : RegisterUiState()
}