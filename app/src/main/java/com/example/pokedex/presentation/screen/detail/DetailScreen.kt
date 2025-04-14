package com.example.pokedex.presentation.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.core.domain.model.PokemonDetail
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    pokemonId: String,
    onBack: () -> Unit
) {

    val viewModel: DetailViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState(initial = DetailUiState.Empty)
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(pokemonId) {
        viewModel.getPokemonDetail(pokemonId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pokemon Detail") },
                navigationIcon = {
                    IconButton(
                        onClick = { onBack.invoke() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Red, titleContentColor = Color.White)
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (uiState) {
                is DetailUiState.Loading -> {
                    CircularProgressIndicator()
                }

                is DetailUiState.Success -> {
                    val pokemon = (uiState as DetailUiState.Success).pokemon
                    PokemonDetailContent(pokemon)
                }

                is DetailUiState.Error -> {
                    val errorMessage = (uiState as DetailUiState.Error).message
                    LaunchedEffect(errorMessage) {
                        scope.launch {
                            snackBarHostState.showSnackbar(
                                message = "Error: $errorMessage"
                            )
                        }
                    }
                }
                else -> Unit
            }
        }
    }
}

@Composable
fun PokemonDetailContent(pokemon: PokemonDetail) {
    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png"

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = pokemon.name,
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "#${pokemon.id} ${pokemon.name?.replaceFirstChar { it.uppercase() }}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Height: ${pokemon.height} | Weight: ${pokemon.weight}",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Types",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        }

        items(pokemon.types) { type ->
            Text(
                text = "- ${type.type?.name?.replaceFirstChar { it.uppercase() }}",
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Abilities",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        }

        items(pokemon.abilities) { ability ->
            Text(
                text = "- ${ability.ability?.name?.replaceFirstChar { it.uppercase() }}",
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Stats",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        }

        items(pokemon.stats) { stat ->
            Text(
                text = "- ${stat.stat?.name?.replaceFirstChar { it.uppercase() }}: ${stat.baseStat}",
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

