package com.example.appparasorteio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appparasorteio.BuildConfig
import com.example.appparasorteio.data.FilmeRepository
import com.example.appparasorteio.network.Movie
import com.example.appparasorteio.network.MovieDetails
import com.example.appparasorteio.network.RetrofitInstance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: FilmeRepository) : ViewModel() {


    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    val savedMovies: StateFlow<List<Movie>> = repository.filmes.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )


    private val _filmeSorteadoDetalhes = MutableStateFlow<MovieDetails?>(null)
    val filmeSorteadoDetalhes = _filmeSorteadoDetalhes.asStateFlow()

    fun fetchMoviesUnified(searchQuery: String) {
        viewModelScope.launch {
            try {
                val moviesByTitle = RetrofitInstance.api.searchMovieByName(
                    query = searchQuery, apiKey = BuildConfig.TMDB_API_KEY
                ).results

                val peopleResponse = RetrofitInstance.api.searchPersonByName(
                    query = searchQuery, apiKey = BuildConfig.TMDB_API_KEY
                )

                val actorMovies = if (peopleResponse.results.isNotEmpty()) {
                    val actorId = peopleResponse.results.first().id
                    RetrofitInstance.api.getMoviesByPerson(
                        personId = actorId, apiKey = BuildConfig.TMDB_API_KEY
                    ).cast
                } else emptyList()


                val combined = (moviesByTitle + actorMovies).distinctBy { it.id }

                val savedIds = savedMovies.value.map { it.id }.toSet()
                val filtered = combined.filter { it.id !in savedIds }

                _movies.value = filtered



                _error.value = if (combined.isEmpty()) "Filme não encontrado." else null

            } catch (e: Exception) {
                _error.value = e.message ?: "Erro desconhecido"
            }
        }
    }


    fun clearMovies() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.salvarFilmesLocal(emptyList())
            }
            _movies.value = emptyList()
        }
    }

    suspend fun addMovie(movie: Movie) {
        withContext(Dispatchers.IO) {
            repository.salvarFilmesLocalFilme(movie)
        }
    }

    fun clearSearch() {
        _movies.value = emptyList()


    }

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMovie(movie)


        }
    }

    fun sortearFilme() {
        val lista = savedMovies.value
        if (lista.isNotEmpty()) {
            val filmeSimples = lista.random()
            carregarDetalhesFilme(filmeSimples.id)
        } else {
            _filmeSorteadoDetalhes.value = null
        }
    }

    fun carregarDetalhesFilme(movieId: Int) {
        viewModelScope.launch {
            try {
                val detalhes = RetrofitInstance.api.getMovieDetails(
                    movieId, apiKey = BuildConfig.TMDB_API_KEY
                )
                _filmeSorteadoDetalhes.value = detalhes
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun clearFilmeSorteado() {
        _filmeSorteadoDetalhes.value = null
    }

    private fun findMovieByDetails(details: MovieDetails): Movie? {
        return savedMovies.value.find { it.id == details.id }
    }

    fun removerFilmeSorteadoAtual() {
        val filmeDetalhes = filmeSorteadoDetalhes.value ?: return
        val movie = findMovieByDetails(filmeDetalhes)
        if (movie != null) {
            deleteMovie(movie)
            clearFilmeSorteado()

        }
    }

}

