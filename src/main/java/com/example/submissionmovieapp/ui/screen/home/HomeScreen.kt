package com.example.submissionmovieapp.ui.screen.home

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.submissionmovieapp.R
import com.example.submissionmovieapp.data.model.Movie
import com.example.submissionmovieapp.helper.ViewModelFactory
import com.example.submissionmovieapp.injection.Injector
import com.example.submissionmovieapp.ui.component.MovieItem
import com.example.submissionmovieapp.ui.component.MyAppBar
import com.example.submissionmovieapp.ui.component.SearchBarComponent
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = viewModel(
        factory = ViewModelFactory(Injector.provideRepository())
    ),
    onActionMenuPressed: () -> Unit,
    navigateToAddMovieScreen: () -> Unit,
    navigateToDetail: (Int) -> Unit
) {
    val state = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val hideAppBar by remember {
        derivedStateOf { state.firstVisibleItemIndex > 0 }
    }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AnimatedVisibility(
                visible = !hideAppBar, enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                MyAppBar(
                    scrollBehavior = scrollBehavior,
                    isNextPage = false,
                    title = "Home",
                    onActionMenuPressed = onActionMenuPressed,
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToAddMovieScreen) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Icon Add",
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay
    ) { paddingValues ->

        viewModel.movieList.let { data ->
            data.let {
                HomeContent(
                    movieList = data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    query = viewModel.query,
                    active = viewModel.active,
                    onQueryChange = viewModel::searchBarQueryState,
                    onActiveChange = viewModel::searchBarActiveState,
                    state = state,
                    paddingValues = paddingValues,
                    onDeleteItem = { id ->
                        viewModel.deleteMovie(id)
                    }
                )
            } ?: run {
                EmptyContent(modifier = modifier.padding(paddingValues))
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    movieList: StateFlow<List<Movie>>,
    modifier: Modifier,
    navigateToDetail: (Int) -> Unit,
    query: State<String>,
    active: State<Boolean>,
    onQueryChange: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    state: LazyListState,
    paddingValues: PaddingValues,
    onDeleteItem: (Int) -> Unit
) {
    val item by movieList.collectAsState()

    Column(
        modifier = modifier
            .padding(
                top = paddingValues.calculateTopPadding() - 30.dp,
                start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                end = paddingValues.calculateEndPadding(LayoutDirection.Rtl),
                bottom = paddingValues.calculateBottomPadding()
            ),
    ) {
        SearchBarComponent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            query = query.value,
            active = active.value,
            onQueryChange = onQueryChange,
            onActiveChange = onActiveChange
        )
        LazyColumn(
            state = state,
        ) {
            items(items = item, key = { it.id }) { movie ->
                MovieItem(
                    movie = movie,
                    onDeleteAction = onDeleteItem,
                    modifier = modifier
                        .clickable { navigateToDetail(movie.id) }
                        .animateItemPlacement(),
                )
            }
        }
    }
}

@Composable
fun EmptyContent(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.empty_vector),
            contentDescription = "Empty",
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = "Movie's Empty",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}

@Preview(
    showBackground = true, device = "id:pixel_5", uiMode = Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun Check(modifier: Modifier = Modifier) {
    EmptyContent()
}