package com.example.submissionmovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.submissionmovieapp.ui.MovieAppMain
import com.example.submissionmovieapp.ui.theme.SubmissionMovieAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SubmissionMovieAPPTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MovieAppMain(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
