package com.example.thebeacon.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thebeacon.ui.addmovie.AddMovieScreen
import com.example.thebeacon.ui.content.ContentDetailScreen
import com.example.thebeacon.ui.login.LoginScreen
import com.example.thebeacon.ui.register.RegisterScreen
import com.example.thebeacon.ui.home.HomeScreen
import com.example.thebeacon.ui.profile.ProfileScreen

sealed class AppRoutes(val route: String) {
    object Login : AppRoutes("login")
    object Register : AppRoutes("register")
    object Home : AppRoutes("home")
    object Profile : AppRoutes("profile")
    object AddMovie : AppRoutes("add_movie")

    object ContentDetail : AppRoutes("content_detail/{id}") {
        fun createRoute(id: String) = "content_detail/$id"
    }
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.Login.route
    ) {

        // LOGIN
        composable(AppRoutes.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(AppRoutes.Home.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onGoToRegister = {
                    navController.navigate(AppRoutes.Register.route)
                }
            )
        }

        // REGISTER
        composable(AppRoutes.Register.route) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(AppRoutes.Login.route) {
                        popUpTo(AppRoutes.Register.route) { inclusive = true }
                    }
                },
                onBackToLogin = {
                    navController.popBackStack()
                }
            )
        }

        // PROFILE
        composable(AppRoutes.Profile.route) {
            ProfileScreen(
                userName = "Usuario Ejemplo",
                onGoToAddMovie = {
                    navController.navigate(AppRoutes.AddMovie.route)
                },
                onLogout = {
                    navController.navigate(AppRoutes.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        // ADD MOVIE
        composable(AppRoutes.AddMovie.route) {
            AddMovieScreen(navController)
        }

        // HOME
        composable(AppRoutes.Home.route) {
            HomeScreen(
                onProfileClick = { navController.navigate(AppRoutes.Profile.route) },
                onGoToDetail = { id ->
                    navController.navigate(AppRoutes.ContentDetail.createRoute(id))
                }
            )
        }

        // (Pendiente) DETALLE REAL DE PELÍCULA — cuando tengas el viewmodel listo
        composable(
            route = AppRoutes.ContentDetail.route
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("id") ?: ""

            ContentDetailScreen(
                movieId = movieId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
