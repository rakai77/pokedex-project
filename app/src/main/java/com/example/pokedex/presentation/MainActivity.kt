package com.example.pokedex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedex.presentation.route.AppRoute
import com.example.pokedex.presentation.screen.detail.DetailScreen
import com.example.pokedex.presentation.screen.home.HomeScreen
import com.example.pokedex.presentation.theme.PokedexTheme
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            PokedexTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = AppRoute.Home.route
                ) {
                    composable(
                        route = AppRoute.Home.route
                    ) {
                        HomeScreen(
                            onNavigate = { pokemonId ->
                                navController.navigate(AppRoute.Detail.route.replace("{pokemonId}", pokemonId))
                            }
                        )
                    }
                    composable(
                        route = AppRoute.Detail.route,
                        arguments = listOf(
                            navArgument("pokemonId") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val pokemonId = backStackEntry.arguments?.getString("pokemonId").orEmpty()
                        DetailScreen(
                            pokemonId = pokemonId,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}