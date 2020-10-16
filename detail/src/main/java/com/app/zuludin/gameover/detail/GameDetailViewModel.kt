package com.app.zuludin.gameover.detail

import androidx.lifecycle.*
import com.app.zuludin.core.data.Resource
import com.app.zuludin.core.domain.model.GameDetail
import com.app.zuludin.core.domain.model.GameStore
import com.app.zuludin.core.domain.usecase.GameUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameDetailViewModel @Inject constructor(
    private val useCase: GameUseCase
) : ViewModel() {

    private val _gameDetail = MutableLiveData<GameDetail>()
    val gameDetail: LiveData<GameDetail> = _gameDetail

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private var _loadingPage = MutableLiveData<Boolean>()
    val loadingPage: LiveData<Boolean> = _loadingPage

    private var _genres = MutableLiveData<List<String>>()
    val genres: LiveData<List<String>> = _genres

    private var _stores = MutableLiveData<List<GameStore>>()
    val stores: LiveData<List<GameStore>> = _stores

    fun loadGameDetail(gameId: Int) {
        viewModelScope.launch {
            _loadingPage.value = true
            val resource = useCase.loadGameDetail(gameId).first()
            if (resource is Resource.Success) {
                _gameDetail.value = resource.data
                _genres.value = resource.data?.genres
                _stores.value = resource.data?.stores
            } else if (resource is Resource.Error) {
                _errorMessage.value = resource.message
            }
            _loadingPage.value = false
        }
    }

    fun isGameFavorite(gameId: Int) = useCase.isGameFavorite(gameId).asLiveData()

    fun setFavoriteGame(gameDetail: GameDetail, isFavorite: Boolean) {
        if (isFavorite) {
            useCase.deleteFavoriteGame(gameDetail.id!!)
        } else {
            useCase.addGameFavorite(gameDetail)
        }
    }
}