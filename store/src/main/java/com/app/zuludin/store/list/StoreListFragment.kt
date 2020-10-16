package com.app.zuludin.store.list

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
import com.app.zuludin.store.R
import com.app.zuludin.store.databinding.FragmentStoreListBinding
import com.app.zuludin.store.di.DaggerStoreComponent
import com.app.zuludin.store.list.views.StoreListAdapter
import kotlinx.android.synthetic.main.error_layout.view.*
import kotlinx.android.synthetic.main.fragment_store_list.*
import javax.inject.Inject

class StoreListFragment : BaseFragment<StoreListViewModel>() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: StoreListViewModel by viewModels {
        factory
    }

    private lateinit var binding: FragmentStoreListBinding

    override fun injectClass() {
        DaggerStoreComponent
            .factory()
            .create(DaggerCoreComponent.factory().create(requireContext()))
            .inject(this)
    }

    override fun initViewModel(): StoreListViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store_list, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun observeViewModel(vm: StoreListViewModel) {
        vm.errorMessage.observe(viewLifecycleOwner, { showSnackbar(it) })

        vm.showErrorLayout.observe(viewLifecycleOwner, {
            it?.let { error ->
                if (error) binding.errorLayout.show()
                else binding.errorLayout.hide()
            }
        })
    }

    override fun setupView(view: View) {
        setupStoreList()
        viewModel.loadGameStore()
        error_layout.try_again_button.setOnClickListener { viewModel.loadGameStore() }
    }

    private fun setupStoreList() {
        recycler_store.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(EqualSpacingLayout(2, 16, EqualSpacingLayout.GRID_MODE))
            setHasFixedSize(true)
            adapter = StoreListAdapter()
        }
    }
}