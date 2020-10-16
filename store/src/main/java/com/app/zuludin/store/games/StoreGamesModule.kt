package com.app.zuludin.store.games

import androidx.lifecycle.ViewModel
import com.app.zuludin.core.ui.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class StoreGamesModule {
    @Binds
    @IntoMap
    @ViewModelKey(StoreGamesViewModel::class)
    abstract fun provideStoreGamesVieWModel(storeGamesViewModel: StoreGamesViewModel): ViewModel
}