package com.example.core.data.remote.reponse

import com.example.core.domain.model.Pokemon
import com.example.core.domain.model.PokemonItem
import com.google.gson.annotations.SerializedName

data class PokemonResponse(

    @field:SerializedName("name")
    val count: Int? = null,

    @field:SerializedName("previous")
    val previous: String? = null,

    @field:SerializedName("next")
    val next: String? = null,

    @field:SerializedName("results")
    val results: List<PokemonItemResponse>,
)

data class PokemonItemResponse(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("url")
    val url: String
)

fun PokemonResponse.toDomain() = Pokemon(
    count = this.count,
    previous = this.previous,
    next = this.next,
    results = this.results.map { it.toDomain() }
)

fun PokemonItemResponse.toDomain() = PokemonItem(
    name = this.name,
    url = this.url
)