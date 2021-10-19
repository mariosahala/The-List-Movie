package com.example.themovie.ui.genre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themovie.ui.listmovie.MovieActivity
import com.example.themovie.R
import com.example.themovie.lokal.SharedPref
import com.example.themovie.models.GenreRespones

class GenreActivity : AppCompatActivity() {
    private lateinit var cvMain: RecyclerView
    private var list: MutableList<GenreRespones.Genre> = mutableListOf()
    private lateinit var sharedPref: SharedPref
    private lateinit var viewModel: GenreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre)
        (supportActionBar as ActionBar).title = "GENRE"
        viewModel = ViewModelProvider(this@GenreActivity)[GenreViewModel::class.java]
        sharedPref = SharedPref(this)
        cvMain = findViewById(R.id.cvMain)
        cvMain.setHasFixedSize(true)

        viewModel.setContent()
        viewModel.resultOnSucces.observe(this@GenreActivity, Observer {
            list.addAll(it)
            setupRecyclerView(list)
        })
    }

    private fun setupRecyclerView(list: MutableList<GenreRespones.Genre>) {
        val genreAdapter = GenreAdapter(list)
        cvMain.adapter = genreAdapter
        cvMain.layoutManager = LinearLayoutManager(this)
        genreAdapter.setOnItemClickCallback(object : GenreAdapter.OnItemClickCallback {
            override fun onItemClicked(data: GenreRespones.Genre) {
                sharedPref.genre = data.id
                val intent = Intent(this@GenreActivity,MovieActivity::class.java)
                intent.putExtra("Genre",data.name)
                startActivity(intent)
            }
        })
    }
}
