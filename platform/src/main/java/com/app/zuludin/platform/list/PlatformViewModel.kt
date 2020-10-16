package com.app.zuludin.platform.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.zuludin.core.data.Resource
import com.app.zuludin.core.domain.model.Platform
import com.app.zuludin.core.domain.usecase.GameUseCase
import com.app.zuludin.core.ui.base.BaseViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlatformViewModel @Inject constructor(
    private val gameUseCase: GameUseCase
) : BaseViewModel() {

    private val _platforms = MutableLiveData<List<Platform>>()
    val platforms: LiveData<List<Platform>> = _platforms

    fun loadPlatformList() {
        viewModelScope.launch {
            showProgress.value = true
            val resource = gameUseCase.loadPlatformsList(true).first()
            if (resource is Resource.Success) {
                _platforms.value = resource.data
                showErrorLayout.value = false
            } else if (resource is Resource.Error) {
                errorMessage.value = resource.message
                showErrorLayout.value = true
            }
            showProgress.value = false
        }
    }
}