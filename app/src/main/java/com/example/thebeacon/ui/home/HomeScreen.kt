package com.example.thebeacon.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thebeacon.ui.content.MovieCard
import com.example.thebeacon.viewmodel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onProfileClick: () -> Unit,
    onGoToDetail: (String) -> Unit,
    movieViewModel: MovieViewModel = viewModel()
) {
    val movies by movieViewModel.movies.collectAsState()
    val isLoading by movieViewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        movieViewModel.loadAllMovies()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            IconButton(onClick = onProfileClick) {
                Icon(Icons.Default.Person, contentDescription = "Perfil")
            }
        }

        Text("Películas", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))


        // 🔥 Chips de filtro
        var selectedGenre by remember { mutableStateOf("all") }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            FilterChip(
                selected = selectedGenre == "all",
                onClick = {
                    selectedGenre = "all"
                    movieViewModel.loadAllMovies()
                },
                label = { Text("Todos") }
            )

            FilterChip(
                selected = selectedGenre == "accion",
                onClick = {
                    selectedGenre = "accion"
                    movieViewModel.filterByGenre("accion")
                },
                label = { Text("Acción") }
            )

            FilterChip(
                selected = selectedGenre == "Sci-Fi",
                onClick = {
                    selectedGenre = "Sci-Fi"
                    movieViewModel.filterByGenre("Sci-Fi")
                },
                label = { Text("Sci-Fi") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator()
            return
        }

        LazyColumn {
            items(movies) { movie ->
                MovieCard(movie) { onGoToDetail(movie.id) }
            }
        }
    }
}
