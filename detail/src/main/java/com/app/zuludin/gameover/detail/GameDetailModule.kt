package com.app.zuludin.gameover.detail

import androidx.lifecycle.ViewModel
import com.app.zuludin.core.ui.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GameDetailModule {
    @Binds
    @IntoMap
    @ViewModelKey(GameDetailViewModel::class)
    abstract fun provideGameDetailViewModel(gameDetailViewModel: GameDetailViewModel): ViewModel
}