package com.example.submissionmovieapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import com.example.submissionmovieapp.data.repository.MainRepository

class DetailViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

//    private val _movieById: MutableStateFlow<Movie> =
//        MutableStateFlow(Movie(0, "", "", "", "", 0.0, R.drawable.sample_picture))
//    val movie: StateFlow<Movie> get() = _movieById

    fun getDetailMovie(id: Int) = mainRepository.getMovieById(id)

}