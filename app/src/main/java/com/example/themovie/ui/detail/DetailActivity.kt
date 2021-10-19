package com.example.themovie.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.themovie.R
import com.example.themovie.lokal.SharedPref

class DetailActivity : AppCompatActivity() {
    private lateinit var ivMoviePoster : ImageView
    private lateinit var ivMovieTitle : ImageView
    private lateinit var tvMovieTitle : TextView
    private lateinit var tvMovieRelease : TextView
    private lateinit var tvDeskripsi : TextView

    private lateinit var viewModel: DetailViewModel
    private lateinit var sharedPref : SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        sharedPref = SharedPref(this)
        ivMoviePoster= findViewById(R.id.iv_movie_poster)
        ivMovieTitle= findViewById(R.id.iv_movie_title)
        tvMovieTitle= findViewById(R.id.tv_movie_title_detail)
        tvMovieRelease= findViewById(R.id.tv_movie_release_date)
        tvDeskripsi = findViewById(R.id.tv_deskripsi)
        val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

        val movieId = sharedPref.movieId
        viewModel.setContent(movieId)
        viewModel.resutlOnSucces.observe(this,{
            tvMovieTitle.text= it.originalTitle
            tvMovieRelease.text = it.releaseDate
            tvDeskripsi.text = it.overview

            Glide.with(this)
                .load(IMAGE_BASE+it.backdropPath)
                .into(ivMoviePoster)

            Glide.with(this)
                .load(IMAGE_BASE+it.posterPath)
                .into(ivMovieTitle)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home->{
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}