package com.example.appparasorteio.network


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TMDBApi {
    @GET("search/movie")
    suspend fun searchMovieByName(
        @Query("query") query: String,
        @Query("api_key") apiKey: String
    ): MovieResponse

    @GET("search/person")
    suspend fun searchPersonByName(
        @Query("query") query: String,
        @Query("api_key") apiKey: String
    ): PersonSearchResponse

    @GET("person/{person_id}/movie_credits")
    suspend fun getMoviesByPerson(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String
    ): MovieCreditsResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "pt-BR"
    ): MovieDetails

}