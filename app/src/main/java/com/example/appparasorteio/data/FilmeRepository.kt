package com.example.appparasorteio.data

import com.example.appparasorteio.network.RetrofitInstance
import com.example.appparasorteio.network.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FilmeRepository @Inject constructor(
    private val dao: FilmeDao,
    private val apiKey: String
) {

    val filmes: Flow<List<Movie>> = dao.getTodosFilmes().map { entities: List<FilmeEntity> ->
        entities.map { entity: FilmeEntity ->
            entity.toMovie()
        }
    }


    suspend fun buscarFilmes(query: String): List<Movie> {
        val filmesByTitle = RetrofitInstance.api.searchMovieByName(query, apiKey).results
        val pessoas = RetrofitInstance.api.searchPersonByName(query, apiKey)
        val filmesDoAtor = if (pessoas.results.isNotEmpty()) {
            RetrofitInstance.api.getMoviesByPerson(pessoas.results.first().id, apiKey).cast
        } else emptyList()

        return (filmesByTitle + filmesDoAtor).distinctBy { it.id }
    }

    suspend fun salvarFilmesLocal(filmes: List<Movie>) {
        dao.inserirFilmes(filmes.map { it.toEntity() })
    }

    suspend fun salvarFilmesLocalFilme(filme: Movie) {
        dao.inserirFilmes(listOf(filme.toEntity()))
    }

    suspend fun deleteMovie(filme: Movie) {
        val entity = dao.getFilmePorTmdbId(filme.id)
        entity?.let {
            dao.delete(it)
        }
    }


}
