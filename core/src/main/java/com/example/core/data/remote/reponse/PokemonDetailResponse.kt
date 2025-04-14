package com.example.core.data.remote.reponse

import com.example.core.domain.model.AbilitiesItem
import com.example.core.domain.model.Ability
import com.example.core.domain.model.FormsItem
import com.example.core.domain.model.PokemonDetail
import com.example.core.domain.model.Species
import com.example.core.domain.model.Stat
import com.example.core.domain.model.StatsItem
import com.example.core.domain.model.Type
import com.example.core.domain.model.TypesItem
import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(

	@field:SerializedName("types")
	val types: List<TypesItemResponse>,

	@field:SerializedName("weight")
	val weight: Int? = null,

	@field:SerializedName("abilities")
	val abilities: List<AbilitiesItemResponse>,

	@field:SerializedName("species")
	val species: SpeciesResponse? = null,

	@field:SerializedName("stats")
	val stats: List<StatsItemResponse>,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("forms")
	val forms: List<FormsItemResponse>,

	@field:SerializedName("height")
	val height: Int? = null,
)

data class StatResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class StatsItemResponse(

	@field:SerializedName("stat")
	val stat: StatResponse? = null,

	@field:SerializedName("base_stat")
	val baseStat: Int? = null,

	@field:SerializedName("effort")
	val effort: Int? = null
)

data class FormsItemResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class SpeciesResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)


data class TypesItemResponse(

	@field:SerializedName("slot")
	val slot: Int? = null,

	@field:SerializedName("type")
	val type: TypeResponse? = null
)

data class AbilityResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class AbilitiesItemResponse(

	@field:SerializedName("is_hidden")
	val isHidden: Boolean,

	@field:SerializedName("ability")
	val ability: AbilityResponse? = null,

	@field:SerializedName("slot")
	val slot: Int? = null
)

data class TypeResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

fun TypeResponse.toDomain() = Type(
	name = this.name,
	url = this.url
)

fun TypesItemResponse.toDomain() = TypesItem(
	slot = this.slot,
	type = this.type?.toDomain() ?: Type()
)

fun PokemonDetailResponse.toDomain() = PokemonDetail(
	types = this.types.map { it.toDomain() },
	weight = this.weight,
	abilities = this.abilities.map { it.toDomain() },
	species = this.species?.toDomain() ?: Species(),
	stats = this.stats.map { it.toDomain() },
	name = this.name,
	id = this.id,
	forms = this.forms.map { it.toDomain() },
	height = this.height
)

fun AbilityResponse.toDomain() = Ability(
	name = this.name,
	url = this.url

)

fun AbilitiesItemResponse.toDomain() = AbilitiesItem(
	isHidden = this.isHidden,
	ability = this.ability?.toDomain(),
	slot = this.slot
)

fun FormsItemResponse.toDomain() = FormsItem(
	name = this.name,
	url = this.url
)

fun StatResponse.toDomain() = Stat(
	name = this.name,
	url = this.url
)

fun StatsItemResponse.toDomain() = StatsItem(
	stat = this.stat?.toDomain() ?: Stat(),
	baseStat = this.baseStat,
	effort = this.effort
)

fun SpeciesResponse.toDomain() = Species(
	name = this.name,
	url = this.url
)