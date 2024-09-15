package com.example.submissionmovieapp.data.model

data class Movie (
    val id: Int,
    val name: String,
    val description: String,
    val director: String,
    val release: String,
    val rate: Double,
    val image: Int
)