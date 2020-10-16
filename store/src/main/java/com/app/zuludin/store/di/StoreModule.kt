package com.app.zuludin.store.di

import com.app.zuludin.core.domain.usecase.GameInteractor
import com.app.zuludin.core.domain.usecase.GameUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class StoreModule {
    @Binds
    abstract fun provideGameUseCase(interactor: GameInteractor): GameUseCase
}