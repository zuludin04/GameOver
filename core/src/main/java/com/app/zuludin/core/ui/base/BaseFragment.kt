package com.app.zuludin.core.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    private lateinit var viewModel: VM

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectClass()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = initViewModel()
        if (activity != null) setupView(view)
        observeViewModel(viewModel)
    }

    abstract fun observeViewModel(vm: VM)
    abstract fun injectClass()
    abstract fun setupView(view: View)
    abstract fun initViewModel(): VM
}