package com.example.themovie.models


import com.google.gson.annotations.SerializedName

data class GenreRespones(
    @SerializedName("genres")
    val genres: List<Genre>
) {
    data class Genre(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )
}