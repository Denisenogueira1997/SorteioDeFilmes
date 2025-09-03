package com.example.appparasorteio.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "filmes")
data class FilmeEntity(
    @PrimaryKey(autoGenerate = true)
    val localId: Int = 0,
    val tmdbId: Int,
    val title: String,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String?
)

