package com.example.core.data.remote.service

import com.example.core.data.remote.reponse.PokemonDetailResponse
import com.example.core.data.remote.reponse.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemonList() : Response<PokemonResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id") id: String
    ) : Response<PokemonDetailResponse>


}