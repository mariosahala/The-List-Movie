package com.example.themovie.ui.listmovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themovie.R
import com.example.themovie.lokal.SharedPref
import com.example.themovie.models.ListMovieResponse
import com.example.themovie.ui.detail.DetailActivity

class MovieActivity : AppCompatActivity() {
    private lateinit var rvMovie: RecyclerView
    private var list: ArrayList<ListMovieResponse.Result> = arrayListOf()
    private lateinit var sharedPref: SharedPref
    private lateinit var viewModel: MovieViewModel
    private lateinit var gridLayoutManager: GridLayoutManager
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val genreName = intent.getStringExtra("Genre")
        (supportActionBar as ActionBar).title = genreName

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        sharedPref = SharedPref(this)
        val genre = sharedPref.genre
        val pageCurent = sharedPref.page
        rvMovie = findViewById(R.id.rv_movie)
        rvMovie.setHasFixedSize(true)

        //setup adapter
        val movieAdapter = MovieAdapter(list)
        rvMovie.adapter = movieAdapter
        gridLayoutManager = GridLayoutManager(this, 2)
        rvMovie.layoutManager = gridLayoutManager
        movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallbackMovie {
            override fun onItemClicked(data: ListMovieResponse.Result) {
                sharedPref.movieId = data.id
                startActivity(Intent(this@MovieActivity, DetailActivity::class.java))
            }
        })
        pagination()

        viewModel.setContent(genre, pageCurent)
        viewModel.resultOnSucces.observe(this, {
            list.addAll(it)
            movieAdapter.notifyDataSetChanged()
        })
    }

    private fun pagination(){
        var totalItemCount : Int
        sharedPref = SharedPref(this)
        val genre = sharedPref.genre

        rvMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                totalItemCount = gridLayoutManager.itemCount
                val lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition()

                if (totalItemCount-1 == lastVisibleItem) {
                    viewModel.setContent(genre, page++)
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}