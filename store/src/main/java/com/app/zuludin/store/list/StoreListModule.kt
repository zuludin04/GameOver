package com.app.zuludin.store.list

import androidx.lifecycle.ViewModel
import com.app.zuludin.core.ui.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class StoreListModule {
    @Binds
    @IntoMap
    @ViewModelKey(StoreListViewModel::class)
    abstract fun provideStoreListViewModel(viewModel: StoreListViewModel): ViewModel
}