package com.example.core.domain.usecase

import com.example.core.data.BaseResult
import com.example.core.domain.model.Pokemon
import com.example.core.domain.model.PokemonDetail
import com.example.core.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class PokemonUseCase(
    private val repository: PokemonRepository
) {

    suspend fun invoke() : Flow<BaseResult<Pokemon>> {
        return repository.getPokemonList()
    }

    suspend fun invokeDetail(id: String) : Flow<BaseResult<PokemonDetail>> {
        return repository.getPokemonDetail(id)
    }
}