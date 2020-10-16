package com.app.zuludin.home.di

import com.app.zuludin.core.di.CoreComponent
import com.app.zuludin.home.GameModule
import com.app.zuludin.home.HomeFragment
import dagger.Component

@HomeScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [HomeModule::class, GameModule::class]
)
interface HomeComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): HomeComponent
    }

    fun inject(homeFragment: HomeFragment)
}