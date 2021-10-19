package com.example.themovie.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themovie.models.DetailResponse
import com.example.themovie.services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel :ViewModel(){
    val resutlOnSucces = MutableLiveData<DetailResponse>()

    fun setContent(movieId: Int){
        RetrofitClient.instance.getMovieDetail(movieId).enqueue(object :Callback<DetailResponse>{
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                val data = response.body()
                data.let {
                    resutlOnSucces.postValue(it)
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
            }

        })
    }
}