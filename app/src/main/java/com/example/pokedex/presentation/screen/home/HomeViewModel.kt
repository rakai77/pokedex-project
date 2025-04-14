package com.example.pokedex.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.BaseResult
import com.example.core.domain.usecase.PokemonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: PokemonUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Empty)
    val uiState = _uiState.asStateFlow()

    fun getPokemonList() {
        viewModelScope.launch {
            _uiState.update {
                HomeUiState.Loading(isLoading = true)
            }
            useCase.invoke().collect { result ->
                _uiState.update {
                    HomeUiState.Loading(isLoading = false)
                }
                when(result){
                    is BaseResult.Success -> {
                        _uiState.update {
                            HomeUiState.Success(result.data)
                        }
                    }
                    is BaseResult.Error -> {
                        _uiState.update {
                            HomeUiState.Error(result.errorCode, result.errorMessage)
                        }
                    }
                }
            }
        }
    }
}