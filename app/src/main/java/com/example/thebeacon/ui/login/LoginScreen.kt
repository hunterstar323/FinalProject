package com.example.thebeacon.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thebeacon.viewmodel.AuthViewModel
import com.example.thebeacon.viewmodel.AuthViewModelFactory
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.thebeacon.util.SessionManager

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onGoToRegister: () -> Unit,
    viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory())
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Cuando se recibe loginResponse con token → guardamos sesión y navegamos
    uiState.loginResponse?.let { resp ->
        // guardamos token/uid localmente y navegamos
        val session = SessionManager(context)
        session.saveAuth(resp.idToken, resp.refreshToken, resp.uid, resp.email)
        LaunchedEffect(resp) {
            viewModel.resetState()
            onLoginSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Iniciar Sesión", style = MaterialTheme.typography.headlineMedium)

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
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.isLoading
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                if (email.isBlank() || password.isBlank()) {
                    // mostrar error en UI
                } else {
                    viewModel.login(email.trim(), password)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.isLoading
        ) {
            Text("Ingresar")
        }

        Spacer(Modifier.height(8.dp))

        TextButton(onClick = { onGoToRegister() }) {
            Text("Crear cuenta")
        }

        Spacer(Modifier.height(10.dp))

        uiState.error?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        if (uiState.isLoading) {
            Spacer(Modifier.height(12.dp))
            CircularProgressIndicator()
        }
    }
}
