package com.example.core.domain.model


data class PokemonDetail(
    val types: List<TypesItem>,
    val weight: Int? = null,
    val abilities: List<AbilitiesItem>,
    val species: Species? = null,
    val stats: List<StatsItem>,
    val name: String? = null,
    val id: Int? = null,
    val forms: List<FormsItem>,
    val height: Int? = null,

    )

data class Stat(
    val name: String? = null,
    val url: String? = null
)

data class StatsItem(
    val stat: Stat? = null,
    val baseStat: Int? = null,
    val effort: Int? = null
)

data class FormsItem(
    val name: String? = null,
    val url: String? = null
)

data class Species(
    val name: String? = null,
    val url: String? = null
)


data class TypesItem(
    val slot: Int? = null,
    val type: Type? = null
)

data class Ability(
    val name: String? = null,
    val url: String? = null
)

data class AbilitiesItem(
    val isHidden: Boolean,
    val ability: Ability? = null,
    val slot: Int? = null
)

data class Type(
    val name: String? = null,
    val url: String? = null
)