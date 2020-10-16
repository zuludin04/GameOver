package com.app.zuludin.store.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zuludin.core.di.DaggerCoreComponent
import com.app.zuludin.core.ui.EqualSpacingLayout
import com.app.zuludin.core.ui.GameAdapter
import com.app.zuludin.core.ui.ViewModelFactory
import com.app.zuludin.core.ui.base.BaseFragment
import com.app.zuludin.core.utils.extensions.hide
import com.app.zuludin.core.utils.extensions.show
import com.app.zuludin.core.utils.extensions.showSnackbar
import com.app.zuludin.store.R
import com.app.zuludin.store.databinding.FragmentStoreGamesBinding
import com.app.zuludin.store.di.DaggerStoreComponent
import kotlinx.android.synthetic.main.error_layout.view.*
import kotlinx.android.synthetic.main.fragment_store_games.*
import javax.inject.Inject

class StoreGamesFragment : BaseFragment<StoreGamesViewModel>() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel by viewModels<StoreGamesViewModel> {
        factory
    }

    private val storeArgs by navArgs<StoreGamesActivityArgs>()
    private lateinit var binding: FragmentStoreGamesBinding

    override fun injectClass() {
        DaggerStoreComponent
            .factory()
            .create(DaggerCoreComponent.factory().create(requireContext()))
            .inject(this)
    }

    override fun initViewModel(): StoreGamesViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store_games, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun observeViewModel(vm: StoreGamesViewModel) {
        vm.errorMessage.observe(viewLifecycleOwner, { showSnackbar(it) })

        vm.showErrorLayout.observe(viewLifecycleOwner, {
            it?.let { error ->
                if (error) binding.errorLayout.show()
                else binding.errorLayout.hide()
            }
        })
    }

    override fun setupView(view: View) {
        setupGamesList()
        viewModel.loadStoreGames(storeArgs.storeId)
        error_layout.try_again_button.setOnClickListener { viewModel.loadStoreGames(storeArgs.storeId) }
    }

    private fun setupGamesList() {
        recycler_games_store.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                EqualSpacingLayout(
                    spacing = 16,
                    mode = EqualSpacingLayout.VERTICAL_MODE
                )
            )
            setHasFixedSize(true)
            adapter = GameAdapter { v, id ->
                v.findNavController().navigate(StoreGamesFragmentDirections.navStoreToDetail(id))
            }
        }
    }
}