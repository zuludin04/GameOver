package com.app.zuludin.platform.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.app.zuludin.core.di.DaggerCoreComponent
import com.app.zuludin.core.ui.EqualSpacingLayout
import com.app.zuludin.core.ui.ViewModelFactory
import com.app.zuludin.core.ui.base.BaseFragment
import com.app.zuludin.core.utils.extensions.hide
import com.app.zuludin.core.utils.extensions.show
import com.app.zuludin.core.utils.extensions.showSnackbar
import com.app.zuludin.platform.R
import com.app.zuludin.platform.databinding.FragmentPlatformBinding
import com.app.zuludin.platform.di.DaggerPlatformComponent
import com.app.zuludin.platform.list.views.PlatformAdapter
import kotlinx.android.synthetic.main.error_layout.view.*
import kotlinx.android.synthetic.main.fragment_platform.*
import javax.inject.Inject

class PlatformFragment : BaseFragment<PlatformViewModel>() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: PlatformViewModel by viewModels {
        factory
    }

    private lateinit var binding: FragmentPlatformBinding

    override fun injectClass() {
        DaggerPlatformComponent
            .factory()
            .create(DaggerCoreComponent.factory().create(requireContext()))
            .inject(this)
    }

    override fun initViewModel(): PlatformViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_platform, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun observeViewModel(vm: PlatformViewModel) {
        vm.errorMessage.observe(viewLifecycleOwner, { showSnackbar(it) })

        vm.showErrorLayout.observe(viewLifecycleOwner, {
            if (it != null) {
                if (it) binding.errorLayout.show()
                else binding.errorLayout.hide()
            }
        })
    }

    override fun setupView(view: View) {
        setupPlatformList()
        viewModel.loadPlatformList()
        error_layout.try_again_button.setOnClickListener { viewModel.loadPlatformList() }
    }

    private fun setupPlatformList() {
        recycler_platform.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(EqualSpacingLayout(2, 16, EqualSpacingLayout.GRID_MODE))
            setHasFixedSize(true)
            adapter = PlatformAdapter()
        }
    }
}