package com.example.submissionmovieapp.ui.screen.detail

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.submissionmovieapp.R
import com.example.submissionmovieapp.data.model.Movie
import com.example.submissionmovieapp.helper.ViewModelFactory
import com.example.submissionmovieapp.injection.Injector
import com.example.submissionmovieapp.ui.component.MyAppBar
import com.example.submissionmovieapp.ui.component.RateComponent

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injector.provideRepository())
    ),
    detailScreenId: Int,
    onBackPressed: () -> Unit
) {
    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val data = viewModel.getDetailMovie(detailScreenId)
    println(detailScreenId)
    Scaffold (
        topBar = {
            MyAppBar(scrollBehavior = scrollBehavior, isNextPage = true, title = "Detail Movie", onBackPress = onBackPressed)
        }
    ) { paddingValue ->
        DetailContent(movie = data, modifier = modifier.padding(paddingValue), scrollState = scrollState,)
    }
}

@Composable
fun DetailContent(modifier: Modifier = Modifier, movie: Movie, scrollState: ScrollState) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            .verticalScroll(scrollState)
    ) {
        Header(movie = movie)
        Text(
            text = "Description", style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 20.sp
            ),
            modifier = Modifier
                .padding(top = 10.dp)
        )
        HorizontalDivider(
            color = Color.Black,
            modifier = Modifier
                .height(1.dp)
                .padding(top = 10.dp)
        )
        Text(
            modifier = Modifier.padding(top = 15.dp),
            text = movie.description,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Light)
        )
    }
}

@Composable
fun Header(modifier: Modifier = Modifier, movie: Movie) {
    Row(
        modifier = modifier
            .fillMaxWidth()

    ) {
        Image(
            painter = painterResource(id = movie.image),
            contentDescription = movie.name,
            modifier = Modifier
                .size(height = 200.dp, width = 120.dp)
                .clip(MaterialTheme.shapes.small),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(start = 10.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(
                        Color.Red
                    )
                    .padding(10.dp)
            ) {
                Text(
                    text = movie.name,
                    style = MaterialTheme.typography.titleSmall.copy(color = Color.White)
                )
            }
            Text(
                text = "Director: ${movie.director}",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = 14.sp
                ),
                modifier = Modifier.padding(vertical = 5.dp)
            )
            Text(
                text = "Release: ${movie.release}",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = 14.sp
                ),
                modifier = Modifier.padding(vertical = 5.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Rate: ", style = MaterialTheme.typography.titleSmall.copy(
                        fontSize = 14.sp
                    ), modifier = Modifier.padding(vertical = 5.dp)
                )
                RateComponent(
                    rate = movie.rate.toFloat(),
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
        }
    }
}

@Preview(
    device = "id:pixel_5", showBackground = true, uiMode = Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun Check(modifier: Modifier = Modifier) {

    val scrollState = rememberScrollState()

    DetailContent(
        movie = Movie(
            id = 0,
            name = "Fast and Furious 6",
            director = "Oprak",
            release = "2024",
            rate = 4.12,
            image = R.drawable.sample_picture,
            description = "Hala Madridsta"
        ),
        scrollState = scrollState
    )
}