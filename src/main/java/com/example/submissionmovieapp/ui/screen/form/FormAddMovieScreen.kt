package com.example.submissionmovieapp.ui.screen.form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.submissionmovieapp.helper.FormField
import com.example.submissionmovieapp.helper.ViewModelFactory
import com.example.submissionmovieapp.injection.Injector
import com.example.submissionmovieapp.ui.component.InputField
import com.example.submissionmovieapp.ui.component.MyAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormAddMovieScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    viewModel: FormViewModel = viewModel(factory = ViewModelFactory(Injector.provideRepository())),
    onSendMovie: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val formData by viewModel.formData.collectAsState()

    Scaffold(
        topBar = {
            MyAppBar(
                scrollBehavior = scrollBehavior,
                isNextPage = true,
                title = "Form Add Movie",
                onBackPress = onBackPressed
            )
        }
    ) { paddingValues ->
        FormAddMovieContent(
            modifier = modifier.padding(paddingValues),
            formField = formData,
            nameChange = viewModel::updateName,
            directorChange = viewModel::updateDirector,
            descriptionChange = viewModel::updateDescription,
            rateChange = viewModel::updateRate,
            releaseChange = viewModel::updateRelease
        ) {
            viewModel.addMovie()
            onSendMovie()
        }
    }
}


@Composable
fun FormAddMovieContent(
    modifier: Modifier = Modifier,
    formField: FormField,
    nameChange: (String) -> Unit,
    directorChange: (String) -> Unit,
    descriptionChange: (String) -> Unit,
    rateChange: (String) -> Unit,
    releaseChange: (String) -> Unit,
    submitForm: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        InputField(label = "Name", value = formField.name, onValueChange = nameChange)
        InputField(label = "Director", value = formField.director, onValueChange = directorChange)
        InputField(label = "Description", value = formField.description, onValueChange = descriptionChange)
        InputField(label = "Release", value = formField.release, onValueChange = releaseChange)
        InputField(label = "Rate", value = formField.rate.toString(), onValueChange = rateChange, type = KeyboardType.Number)
        Button(onClick = submitForm, modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp)) {
            Text("Tambah")
        }
    }
}
