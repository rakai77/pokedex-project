package com.example.pokedex.presentation

import android.content.Context
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
import com.example.pokedex.presentation.screen.login.LoginScreen
import com.example.pokedex.presentation.screen.profile.ProfileScreen
import com.example.pokedex.presentation.screen.register.RegisterScreen
import com.example.pokedex.presentation.theme.PokedexTheme
import com.example.pokedex.utils.SharePref
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            PokedexTheme {
                MyApp(this@MainActivity)
            }
        }
    }
}

@Composable
fun MyApp(context: Context) {
    val navController = rememberNavController()

    val user = SharePref.getUser(context)
    NavHost(
        navController = navController,
        startDestination = if (user != null) AppRoute.Home.route else AppRoute.Login.route
    ) {
        composable(
            route = AppRoute.Login.route
        ) {
            LoginScreen(
                navController = navController,
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            route = AppRoute.Register.route
        ) {
            RegisterScreen(
                navController = navController,
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            route = AppRoute.Home.route
        ) {
            HomeScreen(
                navController = navController,
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
        composable(
            route = AppRoute.Profile.route
        ) {
            ProfileScreen(
                navController = navController,
                onBack = { navController.popBackStack() }
            )
        }
    }
}