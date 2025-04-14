package com.example.core.domain.model

data class Pokemon(
    val count: Int? = null,
    val previous: String? = null,
    val next: String? = null,
    val results: List<PokemonItem> = emptyList(),
)

data class PokemonItem(
    val name: String,
    val url: String
)
