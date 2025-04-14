package com.example.pokedex.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.BaseResult
import com.example.core.domain.usecase.PokemonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val useCase: PokemonUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Empty)
    val uiState = _uiState.asStateFlow()

    fun getPokemonDetail(id: String) {
        viewModelScope.launch {
            _uiState.update {
                DetailUiState.Loading(isLoading = true)
            }
            useCase.invokeDetail(id).collect { result ->
                _uiState.update {
                    DetailUiState.Loading(isLoading = false)
                }
                when(result){
                    is BaseResult.Success -> {
                        _uiState.update {
                            DetailUiState.Success(result.data)
                        }
                    }
                    is BaseResult.Error -> {
                        _uiState.update {
                            DetailUiState.Error(result.errorCode, result.errorMessage)
                        }
                    }
                }
            }
        }
    }
}