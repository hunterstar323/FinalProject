package com.example.thebeacon.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thebeacon.ui.content.*

@Composable
fun HomeScreen(
    onProfileClick: () -> Unit,
    onGoToDetail: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            IconButton(onClick = onProfileClick) {
                Icon(Icons.Default.Person, contentDescription = "Perfil")
            }
        }

        Text("Películas y Series", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(sampleContents) { item ->
                ContentCard(item = item) {
                    onGoToDetail(it.id)
                }
            }
        }
    }
}
