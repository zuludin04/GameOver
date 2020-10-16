package com.app.zuludin.platform.games

import androidx.lifecycle.*
import com.app.zuludin.core.data.Resource
import com.app.zuludin.core.domain.model.Game
import com.app.zuludin.core.domain.usecase.GameUseCase
import com.app.zuludin.core.ui.base.BaseViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlatformGamesViewModel @Inject constructor(
    private val useCase: GameUseCase
) : BaseViewModel() {

    private val _platformGames = MutableLiveData<List<Game>>()
    val platformGames: LiveData<List<Game>> = _platformGames

    fun loadPlatformGames(platformId: Int) {
        viewModelScope.launch {
            showProgress.value = true
            val resource = useCase.loadGamesByPlatform(platformId).first()
            if (resource is Resource.Success) {
                _platformGames.value = resource.data
                showErrorLayout.value = false
            } else if (resource is Resource.Error) {
                errorMessage.value = resource.message
                showErrorLayout.value = true
            }
            showProgress.value = false
        }
    }
}