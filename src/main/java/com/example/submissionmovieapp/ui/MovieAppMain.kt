package com.example.submissionmovieapp.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.submissionmovieapp.ui.navigation.Screen
import com.example.submissionmovieapp.ui.screen.about.AboutScreen
import com.example.submissionmovieapp.ui.screen.detail.DetailScreen
import com.example.submissionmovieapp.ui.screen.form.FormAddMovieScreen
import com.example.submissionmovieapp.ui.screen.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieAppMain(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    val backStackEntry by navHostController.currentBackStackEntryAsState()

    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                onActionMenuPressed = { navHostController.navigate(Screen.About.route) },
                navigateToAddMovieScreen = { navHostController.navigate(Screen.Form.route) }
            ) { id ->
                navHostController.navigate(Screen.Detail.createRoute(id))
            }
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("detailId") { type = NavType.IntType })
        ) {
            val id = it.arguments?.getInt("detailId") ?: -1
            println("dapet + $id")
            DetailScreen(detailScreenId = id) {
                navHostController.navigateUp()
            }
        }
        composable(Screen.About.route) {
            AboutScreen {
                navHostController.navigateUp()
            }
        }
        composable(Screen.Form.route) {
            FormAddMovieScreen(onBackPressed = { navHostController.navigateUp() }) {
                navHostController.popBackStack()
                navHostController.navigate(Screen.Home.route) {
                    popUpTo(navHostController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    }
}


@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun SamplePreview() {
    MovieAppMain()
}