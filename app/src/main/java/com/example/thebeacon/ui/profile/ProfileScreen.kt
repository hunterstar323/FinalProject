package com.example.thebeacon.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(
    userName: String = "Usuario Ejemplo",
    onGoToAddMovie: () -> Unit,
    onLogout: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        Text("Perfil", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(32.dp))

        Text("Nombre de usuario:")
        Text(userName, style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onGoToAddMovie() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar Película")
        }


        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = { onLogout() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar Sesión")
        }
    }
}
