package com.example.submissionmovieapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.submissionmovieapp.R

@Composable
fun RateComponent(modifier: Modifier = Modifier, rate: Float) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(Color.Gray)
            .padding(horizontal = 5.dp, vertical = 2.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_star),
            contentDescription = "Icon_star",
            modifier = Modifier.size(20.dp)
                .padding(end = 5.dp)
        )
        Text(text = rate.toString(), style = MaterialTheme.typography.titleSmall.copy(
            color = Color.White
        ))
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun Check(modifier: Modifier = Modifier) {
    RateComponent(rate = 8.0f)
}