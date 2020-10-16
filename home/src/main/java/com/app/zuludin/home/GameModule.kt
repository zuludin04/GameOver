package com.app.zuludin.home

import androidx.lifecycle.ViewModel
import com.app.zuludin.core.ui.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GameModule {
    @Binds
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    abstract fun provideGameViewModel(gameViewModel: GameViewModel): ViewModel
}