package com.example.submissionmovieapp.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissionmovieapp.data.model.Movie
import com.example.submissionmovieapp.data.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private var _movieList: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    val movieList: StateFlow<List<Movie>> get() = _movieList

    private var _query = mutableStateOf("")
    val query: State<String> get() = _query

    private var _active = mutableStateOf(false)
    val active: State<Boolean> get() = _active

    fun addListMovie(movie: Movie) = repository.addMovie(movie)

    fun deleteMovie(id: Int) {
        viewModelScope.launch {
            repository.deleteMovie(id)
        }
    }

    fun searchBarQueryState(query: String) {
        _query.value = query
        viewModelScope.launch {
           repository.searchMovie(query).collect {
               _movieList.value = it
           }
        }
    }

    fun searchBarActiveState(active: Boolean) {
        _active.value = active
    }

    init {
        viewModelScope.launch {
            repository.getAllMovie()
                .catch {
                    _movieList.value = emptyList()
                }
                .collect { result ->
                    _movieList.value = result
                }
        }
    }

}