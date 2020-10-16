package com.app.zuludin.core.di

import com.app.zuludin.core.data.GameRepository
import com.app.zuludin.core.domain.repository.IGameRepository
import dagger.Binds
import dagger.Module

@Module(includes = [DatabaseModule::class, NetworkModule::class])
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(gameRepository: GameRepository): IGameRepository
}