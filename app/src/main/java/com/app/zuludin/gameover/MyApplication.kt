package com.app.zuludin.gameover

import android.app.Application
import com.app.zuludin.core.di.CoreComponent
import com.app.zuludin.core.di.DaggerCoreComponent

open class MyApplication : Application() {
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

}