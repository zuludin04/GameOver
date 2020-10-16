package com.app.zuludin.gameover.favorite.di

import com.app.zuludin.core.domain.usecase.GameInteractor
import com.app.zuludin.core.domain.usecase.GameUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class FavoriteModule {
    @Binds
    abstract fun provideFavoriteModule(interactor: GameInteractor): GameUseCase
}