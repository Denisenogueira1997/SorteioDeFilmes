package com.example.appparasorteio.network


data class MovieResponse(
    val results: List<Movie>
)

data class Movie(
    val id: Int,
    val title: String,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?


)

data class PersonSearchResponse(
    val results: List<PersonResult>
)

data class PersonResult(
    val id: Int, val name: String
)

data class MovieCreditsResponse(
    val cast: List<Movie>
)

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val genres: List<Genre>
)

data class Genre(
    val id: Int, val name: String
)


