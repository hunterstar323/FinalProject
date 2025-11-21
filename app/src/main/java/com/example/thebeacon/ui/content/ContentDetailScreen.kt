package com.example.thebeacon.ui.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thebeacon.data.remote.model.Movie

@Composable
fun ContentDetailScreen(
    movie: Movie,
    onBack: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        IconButton(onClick = onBack) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
        }

        Spacer(Modifier.height(12.dp))

        Text(
            text = movie.title,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
        )

        Spacer(Modifier.height(20.dp))

        Text("Descripción", style = MaterialTheme.typography.titleMedium)
        Text(movie.description, modifier = Modifier.padding(top = 8.dp))

        Spacer(Modifier.height(20.dp))

        Text("Género: ${movie.genre}")

        Spacer(Modifier.height(10.dp))

        Text("⭐ ${movie.average_rating} (${movie.rating_count} votos)")
    }
}
