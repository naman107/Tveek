package com.example.tveek.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.TvShow
import com.example.domain.usecases.GetSearchedShowsUseCase
import com.example.domain.usecases.GetSimilarShowsUseCase
import com.example.domain.usecases.GetTrendingShowsUseCase
import com.example.domain.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowsViewModels(
    private val getTrendingShowsUseCase: GetTrendingShowsUseCase,
    private val getSearchedShowsUseCase: GetSearchedShowsUseCase,
    private val getSimilarShowsUseCase: GetSimilarShowsUseCase
) : ViewModel() {

    private val _trendingShowsData: MutableLiveData<DataState<TvShow>> = MutableLiveData()
    val trendingShowsData: LiveData<DataState<TvShow>>
        get() = _trendingShowsData

    private val _searchedShowsData: MutableLiveData<DataState<TvShow>> = MutableLiveData()
    val searchedShowsData: LiveData<DataState<TvShow>>
        get() = _searchedShowsData

    private val _similarShowsData: MutableLiveData<DataState<TvShow>> = MutableLiveData()
    val similarShowsData: LiveData<DataState<TvShow>>
        get() = _similarShowsData

    private var tvShow: TvShow.Result? = null

    fun addTvShow(show: TvShow.Result) {
        tvShow = show
    }

    fun getTvShow(): TvShow.Result? {
        return tvShow
    }

    fun getTrendingShows() {
        _trendingShowsData.postValue(DataState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            getTrendingShowsUseCase().collect { state ->
                when (state) {
                    is DataState.Success -> {
                        _trendingShowsData.postValue(DataState.Success(state.data))
                    }

                    is DataState.Error -> {
                        _trendingShowsData.postValue(DataState.Error(state.error))
                    }

                    else -> {

                    }
                }
            }
        }
    }

    fun getSearchedShows(showName: String) {
        _searchedShowsData.postValue(DataState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            getSearchedShowsUseCase(showName).collect { state ->
                when (state) {
                    is DataState.Success -> {
                        _searchedShowsData.postValue(DataState.Success(state.data))
                    }

                    is DataState.Error -> {
                        _searchedShowsData.postValue(DataState.Error(state.error))
                    }

                    else -> {

                    }
                }
            }
        }
    }

    fun getSimilarShows(id: Long) {
        _similarShowsData.postValue(DataState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            getSimilarShowsUseCase(id).collect { state ->
                when (state) {
                    is DataState.Success -> {
                        _similarShowsData.postValue(DataState.Success(state.data))
                    }

                    is DataState.Error -> {
                        _similarShowsData.postValue(DataState.Error(state.error))
                    }

                    else -> {

                    }
                }
            }
        }
    }

}