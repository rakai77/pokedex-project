package com.example.pokedex.presentation.screen.home

import com.example.core.domain.model.Pokemon

sealed class HomeUiState {
    data class Success(val pokemon: Pokemon) : HomeUiState()
    data class Error(val code: Int? = null, val message: String?) : HomeUiState()
    data class Loading(val isLoading: Boolean) : HomeUiState()
    data object Empty : HomeUiState()
}