package com.example.themovie.ui.listmovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themovie.models.ListMovieResponse
import com.example.themovie.services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel: ViewModel() {
    val resultOnSucces = MutableLiveData<List<ListMovieResponse.Result>>()

    fun setContent (genreId:Int, page: Int){
        RetrofitClient.instance.getMovieList(genreId,page).enqueue(object :Callback<ListMovieResponse>{
            override fun onResponse(
                call: Call<ListMovieResponse>,
                response: Response<ListMovieResponse>
            ) {
                val data = response.body()?.results
                data?.let {
                    resultOnSucces.postValue(it)
                }
            }

            override fun onFailure(call: Call<ListMovieResponse>, t: Throwable) {
            }

        })

    }
}