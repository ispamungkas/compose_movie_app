package com.example.submissionmovieapp.helper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submissionmovieapp.data.repository.MainRepository
import com.example.submissionmovieapp.ui.screen.detail.DetailViewModel
import com.example.submissionmovieapp.ui.screen.form.FormViewModel
import com.example.submissionmovieapp.ui.screen.home.HomeScreenViewModel

class ViewModelFactory (
    private val mainRepository: MainRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
            return HomeScreenViewModel(mainRepository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(mainRepository) as T
        } else if (modelClass.isAssignableFrom(FormViewModel::class.java)) {
            return FormViewModel(mainRepository) as T
        }

        throw IllegalArgumentException("Unknown viewmodel class")
    }

}