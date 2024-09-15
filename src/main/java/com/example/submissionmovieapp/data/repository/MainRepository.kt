package com.example.submissionmovieapp.data.repository

import com.example.submissionmovieapp.data.model.Movie
import com.example.submissionmovieapp.helper.Utils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MainRepository {

    private var _listMovie = mutableListOf<Movie>()

    init {
        if (_listMovie.isEmpty()) {
            Utils.generateMovieDummy().forEach { movie ->
                _listMovie.add(movie)
            }
        }
    }

    fun getAllMovie(): Flow<List<Movie>> {
        return flowOf(_listMovie)
    }

    fun getMovieById(id: Int): Movie {
        val movie = _listMovie.first { it.id == id }
        return movie
    }

    fun addMovie(movie: Movie): Flow<Boolean> {
        val result = if (!_listMovie.contains(movie)) {
            _listMovie.add(movie)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun deleteMovie(id: Int){
        val getMovie = _listMovie.first { it.id == id }
        _listMovie.remove(getMovie)
    }

    fun searchMovie(query: String): Flow<List<Movie>> {
        return flowOf(_listMovie.filter {
            it.name.contains(query, ignoreCase = true)
        })
    }

    companion object {
        @Volatile
        private var instance: MainRepository? = null

        fun getInstance() = instance ?: run {
            MainRepository().apply {
                instance = this
            }
        }
    }
}