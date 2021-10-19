package com.example.themovie.lokal

import android.content.Context

class SharedPref(context: Context) {
    private val pref = context.getSharedPreferences("myData", Context.MODE_PRIVATE)
    private val Genre_ID = "GENRE_ID"
    private val MOVIE_TITLE = "MOVIE_TITLE"
    private val PAGE = "PAGE"
    private val MOVIE_ID = "MOVIE_ID"

    var movie: String?
        get() = pref?.getString(MOVIE_TITLE, "")
        set(value) {
            value.let {
                pref?.edit()
                    ?.putString(MOVIE_TITLE, it)
                    ?.apply()
            }
        }

    var genre : Int
    get() = pref.getInt(Genre_ID,0)
    set(value) {
        value.let {
            pref.edit()
                .putInt(Genre_ID,it)
                .apply()
        }
    }
    var page : Int
        get() = pref.getInt(PAGE,1)
        set(value) {
            value.let {
                pref.edit()
                    .putInt(PAGE,it)
                    .apply()
            }
        }

    var movieId : Int
        get() = pref.getInt(MOVIE_ID,0)
        set(value) {
            value.let {
                pref.edit()
                    .putInt(MOVIE_ID,it)
                    .apply()
            }
        }
}