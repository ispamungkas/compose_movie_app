package com.example.submissionmovieapp.ui.navigation

sealed class Screen(
    val route: String
) {

    object Home: Screen("home")
    object Detail: Screen("home/{detailId}") {
        fun createRoute(detailId: Int) = "home/$detailId"
    }
    object About: Screen("about")
    object Form: Screen("form")

}