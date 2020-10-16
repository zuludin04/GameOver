package com.app.zuludin.platform.games

import androidx.lifecycle.ViewModel
import com.app.zuludin.core.ui.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PlatformGamesModule {
    @Binds
    @IntoMap
    @ViewModelKey(PlatformGamesViewModel::class)
    abstract fun providePlatformGamesViewModel(platformGamesViewModel: PlatformGamesViewModel): ViewModel
}