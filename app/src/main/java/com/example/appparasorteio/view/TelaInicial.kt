package com.example.appparasorteio.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appparasorteio.view.Componentes.EmptyState
import com.example.appparasorteio.view.Componentes.FilmeSorteadoCard
import com.example.appparasorteio.viewmodel.MovieViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaInicial(
    navController: NavController, viewModel: MovieViewModel
) {
    val filmes by viewModel.savedMovies.collectAsState()
    val filmeSorteadoDetalhes by viewModel.filmeSorteadoDetalhes.collectAsState()

    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onPrimary,
        topBar = {
            TopAppBar(

                title = {
                    Text(
                        "Movie Choice",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                    )
                }, actions = {

                    Surface(
                        modifier = Modifier.size(40.dp),
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primary
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigate("movie_search")
                            }) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Adicionar Filme",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary
                )
            )

        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(innerPadding),
            contentAlignment = Alignment.Center

        ) {
            when {
                filmes.isEmpty() -> {
                    EmptyState(
                        title = "Sua lista está vazia",
                        description = "Sua lista está vazia no momento. Adicione novos filmes para continuar descobrindo o que assistir!",
                        TextButton = "Adicionar filme",
                        botao = { navController.navigate("movie_search") })
                }

                filmeSorteadoDetalhes == null -> {
                    EmptyState(
                        title = "Nenhum filme sorteado",
                        description = "Você ainda não sorteou um filme. Toque no botão abaixo para descobrir sua próxima sessão!",
                        TextButton = "Sortear um filme",
                        botao = { viewModel.sortearFilme() })
                }

                else -> {
                    filmeSorteadoDetalhes?.let { detalhes ->
                        FilmeSorteadoCard(
                            detalhes = detalhes,
                            onJaAssistirClick = { viewModel.removerFilmeSorteadoAtual() },
                            onSortearOutroClick = { remover ->
                                if (remover) {
                                    viewModel.removerFilmeSorteadoAtual()
                                }
                                viewModel.sortearFilme()
                            }
                        )

                    }
                }
            }
        }
    }
}
