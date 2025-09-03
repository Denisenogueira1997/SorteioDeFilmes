package com.example.appparasorteio.view.Componentes

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appparasorteio.view.MoviesSavedScreen
import com.example.appparasorteio.view.TelaInicial
import com.example.appparasorteio.viewmodel.MovieViewModel

@Composable
fun MainScreenWithBottomNav(movieViewModel: MovieViewModel) {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val selectedIndex = remember { mutableStateOf(0) }


    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route


    val showBottomBar = currentRoute != "movie_search"

    Scaffold(bottomBar = {
        if (showBottomBar) {
            NavigationBar(selectedIndex = selectedIndex.value, onInicioClick = {
                selectedIndex.value = 0
                navController.navigate("Tela-Inicial") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = false }
                    launchSingleTop = true
                }
            }, onMeusFilmesClick = {
                selectedIndex.value = 1
                navController.navigate("movies_saved") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = false }
                    launchSingleTop = true
                }
            }, onFilmesassistidosClick = {
                selectedIndex.value = 2
                navController.navigate("movies_watched") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = false }
                    launchSingleTop = true
                }
            })
        }
    }, snackbarHost = {
        SnackbarHost(
            hostState = snackbarHostState, modifier = Modifier.padding(bottom = 10.dp)
        )
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "Tela-Inicial",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("Tela-Inicial") {
                TelaInicial(navController, movieViewModel)
            }
            composable("movies_saved") {
                MoviesSavedScreen(navController, movieViewModel)

            }/*composable("movies_watched") {
                MoviesWatchedScreen(navController, movieViewModel)
            }*/
            composable("movie_search") {
                MovieSearchScreen(navController, movieViewModel, snackbarHostState)
            }
        }
    }
}
