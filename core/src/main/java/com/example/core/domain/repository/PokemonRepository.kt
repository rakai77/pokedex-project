package com.example.core.domain.repository

import com.example.core.data.BaseResult
import com.example.core.domain.model.Pokemon
import com.example.core.domain.model.PokemonDetail
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemonList() : Flow<BaseResult<Pokemon>>

    suspend fun getPokemonDetail(id: String) : Flow<BaseResult<PokemonDetail>>
}