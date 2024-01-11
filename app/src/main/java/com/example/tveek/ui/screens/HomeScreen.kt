package com.example.tveek.ui.screens

import android.annotation.SuppressLint
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.domain.models.TvShow
import com.example.domain.utils.DataState
import com.example.tveek.R
import com.example.tveek.ui.components.ShowPosterView
import com.example.tveek.viewmodels.FavouriteTvShowViewModel
import com.example.tveek.viewmodels.TvShowsViewModels
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    tvShowViewModel: TvShowsViewModels,
    favouriteTvShowViewModel: FavouriteTvShowViewModel,
    navigationAction: () -> Unit
) {
    LaunchedEffect(Unit) {
        tvShowViewModel.getTrendingShows()
        favouriteTvShowViewModel.getFavouriteShows()
    }
    var isSearchIconClicked: Boolean by remember { mutableStateOf(false) }
    val data = if (isSearchIconClicked) {
        tvShowViewModel.searchedShowsData.observeAsState()
    } else {
        tvShowViewModel.trendingShowsData.observeAsState()
    }
    val likedShows = favouriteTvShowViewModel.likedShowsData.observeAsState()
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when (data.value) {
        is DataState.Success -> {
            var shows = (data.value as DataState.Success<TvShow>).data.results
            var searchShow: String by remember { mutableStateOf("") }
            Scaffold(
                snackbarHost = {
                    SnackbarHost(snackbarHostState)
                }
            ) {
                Column {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp, top = 12.dp),
                        value = searchShow, onValueChange = {
                            searchShow = it
                        },
                        trailingIcon = {
                            Box(modifier = Modifier
                                .clickable {
                                    isSearchIconClicked = true
                                    tvShowViewModel.getSearchedShows(searchShow)
                                }
                            ) {
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
                    if(shows.isNullOrEmpty()){
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "No results found!",
                                withDismissAction = true,
                                duration = SnackbarDuration.Indefinite
                            )
                        }
                    }
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
                            val isLikedShow = likedShows.value?.firstOrNull { it.id == show.id } != null
                            ShowPosterView(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth()
                                    .height(182.dp)
                                    .clickable {
                                        tvShowViewModel.addTvShow(show)
                                        navigationAction()
                                    },
                                isLiked = isLikedShow,
                                show = show,
                                viewModel = favouriteTvShowViewModel,
                                fromHomeScreen = true
                            )
                        }
                    }
                }
            }
        }
        is DataState.Error -> {
            Scaffold(
                snackbarHost = {
                    SnackbarHost(snackbarHostState)
                }
            ) {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = "${(data.value as DataState.Error).error}",
                        withDismissAction = true,
                        duration = SnackbarDuration.Indefinite
                    )
                }
            }
        }
        else -> {

        }
    }

}
