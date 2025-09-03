package com.example.appparasorteio.view.Componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appparasorteio.model.MovieItem
import com.example.appparasorteio.viewmodel.MovieViewModel
import kotlinx.coroutines.launch


@Composable
fun MovieSearchScreen(
    navController: NavController,
    viewModel: MovieViewModel = viewModel(),
    snackbarHostState: SnackbarHostState
) {
    var searchQuery by remember { mutableStateOf("") }
    val movies by viewModel.movies.collectAsState()
    val error by viewModel.error.collectAsState()
    val focusManager = LocalFocusManager.current
    var savedMovieIds by remember { mutableStateOf(setOf<Int>()) }

    val coroutineScope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
        ) {
            SearchBar(
                searchQuery = searchQuery, onQueryChange = { searchQuery = it }, onClear = {
                    searchQuery = ""
                    focusManager.clearFocus()
                }, onBack = {
                    navController.popBackStack()
                    viewModel.clearSearch()
                }, modifier = Modifier.weight(1f)
            )



            IconButton(
                onClick = {
                    if (searchQuery.isNotBlank()) {
                        viewModel.fetchMoviesUnified(searchQuery)
                        focusManager.clearFocus()
                    }
                }, modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary, shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        if (error != null) {
            Text(
                text = "Erro: $error",
                color = MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Divider(
            color = MaterialTheme.colorScheme.outlineVariant,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        )
        LazyColumn {
            items(movies) { movie ->
                val isSaved = savedMovieIds.contains(movie.id)



                MovieItem(movie = movie, isSaved = isSaved, onAddClick = { selectedMovie ->
                    coroutineScope.launch {
                        viewModel.addMovie(selectedMovie)
                        savedMovieIds = savedMovieIds + selectedMovie.id

                        val result = snackbarHostState.showSnackbar(
                            message = "Filme adicionado",
                            actionLabel = "Desfazer",
                            duration = SnackbarDuration.Short,
                            withDismissAction = true
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            viewModel.deleteMovie(selectedMovie)
                            savedMovieIds = savedMovieIds - selectedMovie.id
                        }
                    }
                }, onDeleteClick = {})
            }
        }
    }

}





