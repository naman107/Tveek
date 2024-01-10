package com.example.tveek.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.tveek.ui.theme.TVeekTheme
import com.example.tveek.viewmodels.TvShowsViewModels
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

class MainActivity : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TVeekTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen(viewModel: TvShowsViewModels = koinViewModel()) {
    viewModel.getTrendingShows()
    val data = viewModel.trendingShowsData.observeAsState()
    Timber.d("state: ${data.value}")
}
