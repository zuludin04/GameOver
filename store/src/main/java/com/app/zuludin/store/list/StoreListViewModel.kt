package com.app.zuludin.store.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.zuludin.core.data.Resource
import com.app.zuludin.core.domain.model.Store
import com.app.zuludin.core.domain.usecase.GameUseCase
import com.app.zuludin.core.ui.base.BaseViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class StoreListViewModel @Inject constructor(
    private val gameUseCase: GameUseCase
) : BaseViewModel() {

    private val _storeList = MutableLiveData<List<Store>>()
    val storeList: LiveData<List<Store>> = _storeList

    fun loadGameStore() {
        viewModelScope.launch {
            showProgress.value = true
            val resource = gameUseCase.loadStoreList(true).first()
            if (resource is Resource.Success) {
                _storeList.value = resource.data
                showErrorLayout.value = false
            } else if (resource is Resource.Error) {
                errorMessage.value = resource.message
                showErrorLayout.value = true
            }
            showProgress.value = false
        }
    }
}