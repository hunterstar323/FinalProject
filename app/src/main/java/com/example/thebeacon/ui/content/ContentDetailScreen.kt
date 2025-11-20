package com.example.thebeacon.ui.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ContentDetailScreen(
    item: ContentItem,
    onBack: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        IconButton(onClick = onBack) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = item.title,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Resumen", style = MaterialTheme.typography.titleMedium)
        Text(item.summary, modifier = Modifier.padding(top = 8.dp))

        Spacer(modifier = Modifier.height(20.dp))

        Text("Géneros: ${item.genres.joinToString(", ")}")

        Spacer(modifier = Modifier.height(10.dp))

        Text("Disponible en: ${item.platforms.joinToString(" • ")}")
    }
}
