package com.example.thebeacon.ui.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thebeacon.data.remote.model.Movie

@Composable
fun MovieCard(movie: Movie, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() }
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {

            // Placeholder imagen
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(movie.title, style = MaterialTheme.typography.titleMedium)

                Text(
                    movie.genre,
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    "⭐ ${movie.average_rating}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
