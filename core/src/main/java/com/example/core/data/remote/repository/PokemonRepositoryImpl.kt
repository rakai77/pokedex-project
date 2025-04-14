package com.example.core.data.remote.repository

import com.example.core.data.BaseResult
import com.example.core.data.remote.reponse.toDomain
import com.example.core.data.remote.service.ApiService
import com.example.core.domain.model.Pokemon
import com.example.core.domain.model.PokemonDetail
import com.example.core.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class PokemonRepositoryImpl(
    private val apiService: ApiService
) : PokemonRepository {
    override suspend fun getPokemonList(): Flow<BaseResult<Pokemon>> {
        return flow {
            try {
                val response = apiService.getPokemonList()
                if (response.isSuccessful) {
                    val body = response.body()!!
                    emit(BaseResult.Success(body.toDomain()))
                }
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        emit(BaseResult.Error(t.code(), t.message() ?: "Ups, something went wrong!"))
                    }
                    is UnknownHostException -> {
                        emit(BaseResult.Error(null, t.message ?: "Check your internet connection"))
                    }
                    is SocketTimeoutException -> {
                        emit(BaseResult.Error(null, t.message ?: "Timeout"))
                    }
                    else -> emit(BaseResult.Error(null, t.message ?: "Something went wrong"))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getPokemonDetail(id: String): Flow<BaseResult<PokemonDetail>> {
        return flow {
            try {
                val response = apiService.getPokemonDetail(id)
                if (response.isSuccessful) {
                    val body = response.body()!!
                    emit(BaseResult.Success(body.toDomain()))
                }
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        emit(BaseResult.Error(t.code(), t.message() ?: "Ups, something went wrong!"))
                    }
                    is UnknownHostException -> {
                        emit(BaseResult.Error(null, t.message ?: "Check your internet connection"))
                    }
                    is SocketTimeoutException -> {
                        emit(BaseResult.Error(null, t.message ?: "Timeout"))
                    }
                    else -> emit(BaseResult.Error(null, t.message ?: "Something went wrong"))
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}