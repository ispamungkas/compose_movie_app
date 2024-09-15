package com.example.submissionmovieapp.injection

import com.example.submissionmovieapp.data.repository.MainRepository

object Injector {

    fun provideRepository(): MainRepository = MainRepository.getInstance()

}