package com.example.thebeacon.ui.register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thebeacon.viewmodel.AuthViewModel
import com.example.thebeacon.viewmodel.AuthViewModelFactory
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.thebeacon.util.SessionManager

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onBackToLogin: () -> Unit,
    viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory())
) {
    val uiState by viewModel.uiState.collectAsState()
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    // Cuando hay registerResponse → (opcional) puedes guardar uid o navegar
    uiState.registerResponse?.let { resp ->
        LaunchedEffect(resp) {
            // si quieres: guardar uid localmente
            val session = SessionManager(context)
            session.saveAuth("", "", resp.uid, email) // no hay token en register
            viewModel.resetState()
            onRegisterSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Crear Cuenta", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(20.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.isLoading
        )

        Spacer(Modifier.height(10.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.isLoading
        )

        Spacer(Modifier.height(10.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.isLoading
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.register(username.trim(), email.trim(), password)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.isLoading
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(22.dp), strokeWidth = 2.dp)
            } else {
                Text("Registrarse")
            }
        }

        Spacer(Modifier.height(8.dp))

        TextButton(onClick = { onBackToLogin() }) {
            Text("Volver al login")
        }

        uiState.error?.let {
            Spacer(Modifier.height(10.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}
