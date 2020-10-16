package com.app.zuludin.core.di

import android.content.Context
import com.app.zuludin.core.domain.repository.IGameRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface CoreComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun inject(): IGameRepository
}