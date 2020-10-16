package com.app.zuludin.gameover.detail.di

import com.app.zuludin.core.di.CoreComponent
import com.app.zuludin.gameover.detail.GameDetailActivity
import com.app.zuludin.gameover.detail.GameDetailModule
import dagger.Component

@Component(
    dependencies = [CoreComponent::class],
    modules = [DetailModule::class, GameDetailModule::class]
)
@DetailScope
interface DetailComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): DetailComponent
    }

    fun inject(gameDetailActivity: GameDetailActivity)
}