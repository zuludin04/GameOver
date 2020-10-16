package com.app.zuludin.store.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.zuludin.core.data.Resource
import com.app.zuludin.core.domain.model.Game
import com.app.zuludin.core.domain.usecase.GameUseCase
import com.app.zuludin.core.ui.base.BaseViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class StoreGamesViewModel @Inject constructor(
    private val useCase: GameUseCase
) : BaseViewModel() {

    private val _storeGames = MutableLiveData<List<Game>>()
    val storeGames: LiveData<List<Game>> = _storeGames

    fun loadStoreGames(storeId: Int) {
        viewModelScope.launch {
            showProgress.value = true
            val resource = useCase.loadGamesByStore(storeId).first()
            if (resource is Resource.Success) {
                _storeGames.value = resource.data
                showErrorLayout.value = false
            } else if (resource is Resource.Error) {
                errorMessage.value = resource.message
                showErrorLayout.value = true
            }
            showProgress.value = false
        }
    }
}