package com.example.tveek.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.domain.models.TvShow
import com.example.tveek.viewmodels.FavouriteTvShowViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun ShowPosterView(
    modifier: Modifier,
    viewModel: FavouriteTvShowViewModel ? = null,
    isLiked: Boolean = false,
    show: TvShow.Result,
    fromHomeScreen: Boolean
) {
    val painter = rememberAsyncImagePainter(
        ImageRequest
            .Builder(LocalContext.current)
            .data(data = "https://image.tmdb.org/t/p/original${show.poster_path}")
            .build()
    )
    var isLikedShow = mutableStateOf(isLiked)

    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(contentAlignment = Alignment.TopEnd) {
            Column {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(148.dp)
                        .padding(4.dp),
                    painter = painter,
                    contentDescription = "Image",
                    contentScale = ContentScale.FillBounds,
                )
                Text(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    text = show.name,
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                )
            }
            if(fromHomeScreen){
                IconToggleButton(
                    checked = isLikedShow.value,
                    onCheckedChange = {
                        isLikedShow.value = !isLikedShow.value
                        if(isLikedShow.value){
                            viewModel?.saveFavouriteShow(show)
                        }else{
                            viewModel?.deleteFavoriteShow(show.id)
                        }
                    }
                ) {
                    Icon(
                        tint = Color.Red,
                        imageVector = if (isLikedShow.value) {
                            Icons.Filled.Favorite
                        } else {
                            Icons.Default.FavoriteBorder
                        },
                        contentDescription = null
                    )
                }
            }
        }
    }
}

