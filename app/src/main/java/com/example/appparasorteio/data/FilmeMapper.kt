package com.example.appparasorteio.data


import com.example.appparasorteio.network.Movie

fun FilmeEntity.toMovie() = Movie(
    id = this.tmdbId,
    title = this.title,
    overview = this.overview,
    poster_path = this.posterPath,
    release_date = this.releaseDate
)

fun Movie.toEntity() = FilmeEntity(
    tmdbId = this.id,
    title = this.title ?: "",
    overview = this.overview,
    posterPath = this.poster_path,
    releaseDate = this.release_date
)
