package com.example.tveek.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.TvShow
import com.example.domain.repository.ITvRepository
import com.example.domain.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TvShowsViewModels(
    private val repository: ITvRepository
): ViewModel() {

    private val _trendingShowsData: MutableLiveData<DataState<TvShow>> = MutableLiveData()
    val trendingShowsData: LiveData<DataState<TvShow>>
        get() = _trendingShowsData

    private val _searchedShowsData: MutableLiveData<DataState<TvShow>> = MutableLiveData()
    val searchedShowsData: LiveData<DataState<TvShow>>
        get() = _searchedShowsData

    private val _similarShowsData: MutableLiveData<DataState<TvShow>> = MutableLiveData()
    val similarShowsData: LiveData<DataState<TvShow>>
        get() = _similarShowsData

    fun getTrendingShows(){
        _trendingShowsData.postValue(DataState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getTrendingShows()
            withContext(Dispatchers.Main){
                when(result){
                    is DataState.Success -> {
                        _trendingShowsData.postValue(DataState.Success(result.data))
                    }
                    is DataState.Error -> {
                        _trendingShowsData.postValue(DataState.Error(result.error))
                    }
                    else -> {

                    }
                }
            }
        }
    }

    fun getSearchedShows(showName: String){
        _searchedShowsData.postValue(DataState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getSearchedShows(showName)
            withContext(Dispatchers.Main){
                when(result){
                    is DataState.Success -> {
                        _searchedShowsData.postValue(DataState.Success(result.data))
                    }
                    is DataState.Error -> {
                        _searchedShowsData.postValue(DataState.Error(result.error))
                    }
                    else -> {

                    }
                }
            }
        }
    }

    fun getSimilarShows(id: Long){
        _similarShowsData.postValue(DataState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getSimilarShows(id)
            withContext(Dispatchers.Main){
                when(result){
                    is DataState.Success -> {
                        _similarShowsData.postValue(DataState.Success(result.data))
                    }
                    is DataState.Error -> {
                        _similarShowsData.postValue(DataState.Error(result.error))
                    }
                    else -> {

                    }
                }
            }
        }
    }

}