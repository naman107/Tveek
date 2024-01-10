package com.example.tveek.ui.components

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
import androidx.compose.runtime.MutableState
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

@Composable
fun ShowPosterView(
    modifier: Modifier,
    posterUri: String,
    name: String,
    isLiked: MutableState<Boolean>,
) {
    val painter = rememberAsyncImagePainter(
        ImageRequest
            .Builder(LocalContext.current)
            .data(data = posterUri)
            .build()
    )
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
                    text = name,
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                )
            }
            IconToggleButton(
                checked = isLiked.value,
                onCheckedChange = {
                    isLiked.value = !isLiked.value
                }
            ) {
                Icon(
                    tint = Color.Red,
                    imageVector = if (isLiked.value) {
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

