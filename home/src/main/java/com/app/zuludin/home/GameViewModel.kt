package com.app.zuludin.home

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

class GameViewModel @Inject constructor(private val useCase: GameUseCase) : BaseViewModel() {

    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>> = _games

    fun loadGameList() {
        viewModelScope.launch {
            showProgress.value = true
            val resource = useCase.getGameList(true).first()
            if (resource is Resource.Success) {
                _games.value = resource.data
                showErrorLayout.value = false
            } else if (resource is Resource.Error) {
                errorMessage.value = resource.message
                showErrorLayout.value = true
            }
            showProgress.value = false
        }
    }
}