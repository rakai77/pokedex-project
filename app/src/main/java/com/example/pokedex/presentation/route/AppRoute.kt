package com.example.pokedex.presentation.route

sealed class AppRoute(val route: String) {

    data object Home : AppRoute("home")
    data object Detail : AppRoute("detail/{pokemonId}")
}