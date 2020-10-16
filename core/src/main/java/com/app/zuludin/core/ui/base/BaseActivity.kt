package com.app.zuludin.core.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        injectClass()
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        setupView()
    }

    abstract fun injectClass()
    abstract fun layoutId(): Int
    abstract fun setupView()
}