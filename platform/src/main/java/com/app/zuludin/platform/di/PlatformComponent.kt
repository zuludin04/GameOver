package com.app.zuludin.platform.di

import com.app.zuludin.core.di.CoreComponent
import com.app.zuludin.platform.games.PlatformGamesFragment
import com.app.zuludin.platform.games.PlatformGamesModule
import com.app.zuludin.platform.list.PlatformFragment
import com.app.zuludin.platform.list.PlatformListModule
import dagger.Component

@PlatformScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [PlatformModule::class, PlatformListModule::class, PlatformGamesModule::class]
)
interface PlatformComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): PlatformComponent
    }

    fun inject(platformFragment: PlatformFragment)

    fun inject(platformGamesFragment: PlatformGamesFragment)
}