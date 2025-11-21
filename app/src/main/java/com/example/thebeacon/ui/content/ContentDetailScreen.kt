package com.example.thebeacon.ui.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thebeacon.data.remote.model.Movie
import com.example.thebeacon.viewmodel.MovieDetailViewModel

@Composable
fun ContentDetailScreen(
    movieId: String,
    onBack: () -> Unit,
    vm: MovieDetailViewModel = viewModel()
) {
    val movie by vm.movie.collectAsState()
    val loading by vm.isLoading.collectAsState()
    val error by vm.error.collectAsState()

    LaunchedEffect(Unit) {
        vm.loadMovieById(movieId)
    }

    if (loading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    if (error != null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Error: $error")
        }
        return
    }

    movie?.let { data ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
            }

            Spacer(Modifier.height(12.dp))
            Text(data.title, style = MaterialTheme.typography.headlineMedium)

            Spacer(Modifier.height(20.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            )

            Spacer(Modifier.height(20.dp))

            Text("Descripción", style = MaterialTheme.typography.titleMedium)
            Text(data.description)

            Spacer(Modifier.height(20.dp))
            Text("Género: ${data.genre}")
            Text("⭐ ${data.average_rating} (${data.rating_count} votos)")
        }
    }
}
