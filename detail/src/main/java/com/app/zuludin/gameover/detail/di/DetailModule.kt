package com.app.zuludin.gameover.detail.di

import com.app.zuludin.core.domain.usecase.GameInteractor
import com.app.zuludin.core.domain.usecase.GameUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class DetailModule {
    @Binds
    abstract fun provideUseCase(interactor: GameInteractor): GameUseCase
}