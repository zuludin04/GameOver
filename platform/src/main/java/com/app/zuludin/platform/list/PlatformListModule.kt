package com.app.zuludin.platform.list

import androidx.lifecycle.ViewModel
import com.app.zuludin.core.ui.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PlatformListModule {
    @Binds
    @IntoMap
    @ViewModelKey(PlatformViewModel::class)
    abstract fun providePlatformViewModel(platformViewModel: PlatformViewModel): ViewModel
}