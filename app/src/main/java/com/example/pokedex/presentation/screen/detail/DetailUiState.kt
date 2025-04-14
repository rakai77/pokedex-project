package com.example.pokedex.presentation.screen.detail

import com.example.core.domain.model.PokemonDetail

sealed class DetailUiState {
    data class Success(val pokemon: PokemonDetail) : DetailUiState()
    data class Error(val code: Int? = null, val message: String?) : DetailUiState()
    data class Loading(val isLoading: Boolean) : DetailUiState()
    data object Empty : DetailUiState()
}