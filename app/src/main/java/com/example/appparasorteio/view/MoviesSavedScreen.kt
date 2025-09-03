package com.example.appparasorteio.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.appparasorteio.model.MovieItem
import com.example.appparasorteio.viewmodel.MovieViewModel
import kotlinx.coroutines.launch

@Composable
fun MoviesSavedScreen(
    navController: NavController,
    viewModel: MovieViewModel
) {
    val filmes by viewModel.savedMovies.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        containerColor = MaterialTheme.colorScheme.onPrimary,

        ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)

        ) {
            items(filmes) { filme ->
                MovieItem(
                    movie = filme,
                    isSaved = true,
                    onAddClick = { selectedMovie ->
                        coroutineScope.launch {
                            viewModel.addMovie(selectedMovie)
                            val result = snackbarHostState.showSnackbar(
                                message = "Filme adicionado",
                                actionLabel = "Desfazer",
                                duration = SnackbarDuration.Short,
                                withDismissAction = true
                            )
                            if (result == SnackbarResult.ActionPerformed) {
                                viewModel.deleteMovie(selectedMovie)
                            }
                        }
                    },
                    onDeleteClick = { movie ->
                        viewModel.deleteMovie(movie)
                        coroutineScope.launch {
                            val result = snackbarHostState.showSnackbar(
                                message = "Filme removido",
                                actionLabel = "Desfazer",
                                duration = SnackbarDuration.Short,
                                withDismissAction = true
                            )
                            if (result == SnackbarResult.ActionPerformed) {
                                viewModel.addMovie(movie)
                            }
                        }
                    }

                )

            }
        }
    }
}

