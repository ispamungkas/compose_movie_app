package com.example.submissionmovieapp.ui.screen.about

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.submissionmovieapp.R
import com.example.submissionmovieapp.ui.component.MyAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(modifier: Modifier = Modifier, onBackPressed: () -> Unit) {
    val rememberScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            MyAppBar(scrollBehavior = rememberScrollBehavior, isNextPage = true, title = "About", onBackPress = onBackPressed)
        }
    ) { paddingValues ->
        AboutContent(modifier = modifier.padding(paddingValues))
    }
}

@Composable
fun AboutContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.formal),
            contentDescription = "Profile",
            modifier = Modifier
                .border(border = BorderStroke(5.dp, Color.Green), RoundedCornerShape(150.dp))
                .padding(10.dp)
                .clip(
                    RoundedCornerShape(100.dp)
                )
                .size(200.dp)
        )
        Text(
            text = "Ilham Surya Putra Pamungkas",
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic
            ),
            modifier = Modifier.padding(vertical = 15.dp)
        )
        Text(
            text = "Isuryaputrapamungkas@gmail.com",
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic
            ),
        )
    }
}

@Preview(
    showBackground = true, device = "id:pixel_5"
)
@Composable
fun Check(modifier: Modifier = Modifier) {
    AboutContent()
}