package com.example.submissionmovieapp.ui.screen.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissionmovieapp.R
import com.example.submissionmovieapp.data.model.Movie
import com.example.submissionmovieapp.data.repository.MainRepository
import com.example.submissionmovieapp.helper.FormField
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FormViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    private var _formData = MutableStateFlow(FormField())
    val formData : StateFlow<FormField> get() = _formData

    fun updateName(name: String) {
        _formData.value = _formData.value.copy(name = name)
    }

    fun updateDescription(description: String) {
        _formData.value = _formData.value.copy(description = description)
    }

    fun updateRate(rate: String) {
        _formData.value = _formData.value.copy(rate = rate)
    }

    fun updateRelease(release: String) {
        _formData.value = _formData.value.copy(release = release)
    }

    fun updateDirector(director: String) {
        _formData.value = _formData.value.copy(director = director)
    }


    fun addMovie() {
        viewModelScope.launch {
            var lastIndex = 0
            mainRepository.getAllMovie().collect {
                lastIndex = it.last().id + 1
            }
            val movie = Movie(
                id = lastIndex,
                name = _formData.value.name,
                description = _formData.value.description,
                director = _formData.value.director,
                release = _formData.value.release,
                rate = _formData.value.rate.toDouble(),
                image = R.drawable.cek,
            )
            mainRepository.addMovie(movie)

        }

    }
}