package com.example.tveek.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.TvShow
import com.example.domain.usecases.DeleteFavoriteShowUseCase
import com.example.domain.usecases.GetFavouriteShowsUseCase
import com.example.domain.usecases.SaveFavouriteShowsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteTvShowViewModel(
    private val saveFavouriteShowsUseCase: SaveFavouriteShowsUseCase,
    private val getFavouriteShowsUseCase: GetFavouriteShowsUseCase,
    private val deleteFavoriteShowUseCase: DeleteFavoriteShowUseCase
): ViewModel() {

    private val _likedShowsData: MutableLiveData<List<TvShow.Result>> = MutableLiveData()
    val likedShowsData: LiveData<List<TvShow.Result>>
        get() = _likedShowsData

    fun saveFavouriteShow(show: TvShow.Result){
        viewModelScope.launch {
            saveFavouriteShowsUseCase(show)
        }
    }

    fun getFavouriteShows() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavouriteShowsUseCase().collect { shows ->
                _likedShowsData.postValue(shows)
            }
        }
    }

    fun deleteFavoriteShow(id: Long){
        viewModelScope.launch {
            deleteFavoriteShowUseCase(id)
        }
    }

}