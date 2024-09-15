package com.example.submissionmovieapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComponent(
    modifier: Modifier = Modifier,
    query: String,
    active: Boolean,
    onQueryChange: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit
) {
    SearchBar(
        modifier = modifier,
        query = query,
        onQueryChange = { onQueryChange(it) },
        onSearch = { onActiveChange(false) },
        active = active,
        onActiveChange = {
            onActiveChange(it)
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
        },
        trailingIcon = {
            if (active) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Icon Close",
                    modifier = Modifier.clickable {
                        if (query.isNotEmpty()) {
                            onQueryChange("")
                        } else {
                            onActiveChange(false)
                        }
                    },
                )
            }
        },
        placeholder = {
            Text(
                text = "Cari Film", style = MaterialTheme.typography.titleSmall.copy(
                    color = Color.Gray, fontSize = 14.sp
                )
            )
        }
    ) {

    }
}

@Preview(
    showBackground = true
)
@Composable
fun CheckSearchBarComponent(modifier: Modifier = Modifier) {
    var q by remember {
        mutableStateOf("")
    }
    var c by remember {
        mutableStateOf(false)
    }
    SearchBarComponent(
        query = q,
        active = c,
        onActiveChange = {
            c = it
        },
        onQueryChange = {
            q = it
        }
    )
}