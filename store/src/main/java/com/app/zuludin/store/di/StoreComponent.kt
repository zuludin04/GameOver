package com.app.zuludin.store.di

import com.app.zuludin.core.di.CoreComponent
import com.app.zuludin.store.games.StoreGamesFragment
import com.app.zuludin.store.games.StoreGamesModule
import com.app.zuludin.store.list.StoreListFragment
import com.app.zuludin.store.list.StoreListModule
import dagger.Component

@StoreScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [StoreModule::class, StoreListModule::class, StoreGamesModule::class]
)
interface StoreComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): StoreComponent
    }

    fun inject(storeFragment: StoreListFragment)

    fun inject(storeGamesFragment: StoreGamesFragment)
}