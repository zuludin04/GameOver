package com.app.zuludin.gameover.favorite

import androidx.lifecycle.ViewModel
import com.app.zuludin.core.ui.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FavoriteListModule {
    @Binds
    @IntoMap
    @ViewModelKey(FavoriteListViewModel::class)
    abstract fun provideFavoriteListViewModel(favoriteListViewModel: FavoriteListViewModel): ViewModel
}