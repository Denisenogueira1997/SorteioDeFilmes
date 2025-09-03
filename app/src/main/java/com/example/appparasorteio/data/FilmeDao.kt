package com.example.appparasorteio.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FilmeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirFilmes(filmes: List<FilmeEntity>)

    @Query("SELECT * FROM filmes")
    fun getTodosFilmes(): Flow<List<FilmeEntity>>

    @Query("DELETE FROM filmes")
    fun limparFilmes(): Int

    @Delete
    fun delete(filme: FilmeEntity)

    @Query("SELECT * FROM filmes WHERE tmdbId = :tmdbId LIMIT 1")
    suspend fun getFilmePorTmdbId(tmdbId: Int): FilmeEntity?


}

