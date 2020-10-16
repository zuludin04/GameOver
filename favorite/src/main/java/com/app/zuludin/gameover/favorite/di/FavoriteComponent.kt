package com.app.zuludin.gameover.favorite.di

import com.app.zuludin.core.di.CoreComponent
import com.app.zuludin.gameover.favorite.FavoriteListFragment
import com.app.zuludin.gameover.favorite.FavoriteListModule
import dagger.Component

@Component(
    dependencies = [CoreComponent::class],
    modules = [FavoriteModule::class, FavoriteListModule::class]
)
@FavoriteScope
interface FavoriteComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): FavoriteComponent
    }

    fun inject(favoriteListFragment: FavoriteListFragment)
}