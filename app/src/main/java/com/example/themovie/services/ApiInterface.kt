package com.example.themovie.services

import com.example.themovie.models.DetailResponse
import com.example.themovie.models.GenreRespones
import com.example.themovie.models.ListMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("3/genre/movie/list?api_key=be8b6c8aa9a5f4e240bb6093f9849051")
    fun getGenreList(): Call<GenreRespones>

    @GET("3/discover/movie?api_key=be8b6c8aa9a5f4e240bb6093f9849051")
    fun getMovieList(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int? = 1
    ): Call<ListMovieResponse>

    @GET("3/movie/{movie_id}?api_key=be8b6c8aa9a5f4e240bb6093f9849051")
    fun getMovieDetail(@Path("movie_id") movieId: Int): Call<DetailResponse>
}