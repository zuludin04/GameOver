package com.app.zuludin.gameover.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.zuludin.core.domain.model.Game
import com.app.zuludin.core.domain.usecase.GameUseCase
import com.app.zuludin.core.ui.base.BaseViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteListViewModel @Inject constructor(
    private val useCase: GameUseCase
) : BaseViewModel() {

    private val _favorites = MutableLiveData<List<Game>>()
    val favorites: LiveData<List<Game>> = _favorites

    private val _empty = MutableLiveData<Boolean>()
    val empty: LiveData<Boolean> = _empty

    fun loadFavoriteGames() {
        viewModelScope.launch {
            val result = useCase.loadFavoriteGame().first()
            if (result.isNotEmpty()) {
                _favorites.value = result
                _empty.value = false
            } else {
                _empty.value = true
            }
        }
    }
}