package com.example.tveek.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.domain.model.TvShow
import com.example.domain.utils.DataState
import com.example.tveek.R
import com.example.tveek.ui.components.ShowPosterView
import com.example.tveek.viewmodels.TvShowsViewModels

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: TvShowsViewModels,
    navigationAction: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getTrendingShows()
    }
    var isSearchIconClicked: Boolean by remember { mutableStateOf(false) }
    val data = if(isSearchIconClicked){
        viewModel.searchedShowsData.observeAsState()
    }else{
        viewModel.trendingShowsData.observeAsState()
    }

    when (data.value) {
        is DataState.Success -> {
            val shows = (data.value as DataState.Success<TvShow>).data.results
            var searchShow: String by remember { mutableStateOf("") }
            Column {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, end = 12.dp, top = 12.dp)
                    ,
                    value = searchShow, onValueChange = {
                        searchShow = it
                    },
                    trailingIcon = {
                        Box(modifier = Modifier
                            .clickable {
                                isSearchIconClicked = true
                                viewModel.getSearchedShows(searchShow)
                            }
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.ic_search_gray),
                                contentDescription = "Search"
                            )
                        }
                    },
                    placeholder = {
                        Text(
                            text = "Enter you favourite show"
                        )
                    },
                    shape = RoundedCornerShape(24.dp)
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(
                        start = 12.dp,
                        top = 16.dp,
                        end = 12.dp,
                        bottom = 16.dp
                    )
                ) {
                    items(shows) { show ->
                        ShowPosterView(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                                .height(182.dp)
                                .clickable {
                                    viewModel.addTvShow(show)
                                    navigationAction()
                                },
                            posterUri = "https://image.tmdb.org/t/p/original${show.poster_path}",
                            name = show.name,
                            isLiked = remember {
                                mutableStateOf(show.isLiked)
                            }
                        )
                    }
                }
            }
        }

        is DataState.Error -> {
            //Handle error
        }

        else -> {

        }
    }

}
