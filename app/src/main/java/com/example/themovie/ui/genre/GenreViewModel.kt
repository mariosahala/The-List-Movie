package com.example.themovie.ui.genre

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themovie.models.GenreRespones
import com.example.themovie.services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenreViewModel : ViewModel() {
    val resultOnSucces = MutableLiveData<List<GenreRespones.Genre>>()

    fun setContent() {
        RetrofitClient.instance.getGenreList().enqueue(object : Callback<GenreRespones> {
            override fun onResponse(call: Call<GenreRespones>, response: Response<GenreRespones>) {
                val data = response.body()?.genres
                data?.let {
                    resultOnSucces.postValue(it)
                }
            }
            override fun onFailure(call: Call<GenreRespones>, t: Throwable) {
            }
        })
    }
}