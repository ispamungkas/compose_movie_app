package com.example.submissionmovieapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.submissionmovieapp.R
import com.example.submissionmovieapp.data.model.Movie

@Composable
fun MovieItem(modifier: Modifier = Modifier, movie: Movie, onDeleteAction: (Int) -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = movie.image), contentDescription = movie.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(10.dp),
                    )
                    .width(70.dp)
                    .height(110.dp),
            )
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {
                    Text(
                        text = movie.name,
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.Black),
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_star),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                        )
                        Text(
                            modifier = Modifier.padding(start = 5.dp),
                            text = movie.rate.toString(),
                            style = MaterialTheme.typography.titleSmall.copy(color = Color.Black),
                        )
                    }
                }
                Divider(
                    modifier = Modifier
                        .height(1.dp)
                )
                Text(
                    text = movie.description,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(top = 5.dp)
                )
                Button(
                    modifier = Modifier.align(Alignment.End),
                    onClick = {
                        onDeleteAction(movie.id)
                    } ,
                    colors = ButtonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White,
                        disabledContentColor = Color.Black,
                        disabledContainerColor = Color.Gray
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Delete",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SamplePreview() {
    MovieItem(
        movie = Movie(
            id = 0,
            name = "Fast and Furious 6",
            director = "Oprak",
            release = "2024",
            rate = 4.12,
            image = R.drawable.sample_picture,
            description = "Hala Madridsta"
        ),
        onDeleteAction = {}
    )
}