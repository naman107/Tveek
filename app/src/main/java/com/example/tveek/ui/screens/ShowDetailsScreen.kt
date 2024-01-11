package com.example.tveek.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.domain.models.TvShow
import com.example.domain.utils.DataState
import com.example.tveek.ui.components.ShowPosterView
import com.example.tveek.viewmodels.TvShowsViewModels

@Composable
fun ShowDetailsScreen(
    viewModel: TvShowsViewModels
) {
    val tvShow = viewModel.getTvShow()
    LaunchedEffect(Unit) {
        tvShow?.id?.let {
            viewModel.getSimilarShows(it)
        }
    }
    val data = viewModel.similarShowsData.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val painter = rememberAsyncImagePainter(
            ImageRequest
                .Builder(LocalContext.current)
                .data(data = "https://image.tmdb.org/t/p/original${tvShow?.poster_path}")
                .build()
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f),
            painter = painter,
            contentDescription = "Image",
            contentScale = ContentScale.FillBounds,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, start = 8.dp),
            text = "Description",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 4.dp, start = 8.dp, end = 8.dp),
            text = "${tvShow?.overview}",
            color = Color.DarkGray,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, start = 8.dp),
            text = "Similar Shows",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Box {
            when (data.value) {
                is DataState.Success -> {
                    val shows = (data.value as DataState.Success<TvShow>).data.results
                    LazyRow {
                        items(shows) { show ->
                            ShowPosterView(
                                modifier = Modifier
                                    .width(96.dp)
                                    .height(148.dp)
                                    .padding(4.dp),
                                show = show,
                                fromHomeScreen = false
                            )
                        }
                    }
                }

                is DataState.Error -> {
                }

                else -> {
                }
            }
        }
    }
}