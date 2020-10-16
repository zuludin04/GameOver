package com.app.zuludin.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zuludin.core.di.DaggerCoreComponent
import com.app.zuludin.core.ui.EqualSpacingLayout
import com.app.zuludin.core.ui.GameAdapter
import com.app.zuludin.core.ui.ViewModelFactory
import com.app.zuludin.core.ui.base.BaseFragment
import com.app.zuludin.core.utils.extensions.hide
import com.app.zuludin.core.utils.extensions.show
import com.app.zuludin.core.utils.extensions.showSnackbar
import com.app.zuludin.home.databinding.FragmentHomeBinding
import com.app.zuludin.home.di.DaggerHomeComponent
import kotlinx.android.synthetic.main.error_layout.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment<GameViewModel>() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: GameViewModel by viewModels {
        factory
    }

    private lateinit var binding: FragmentHomeBinding

    override fun injectClass() {
        DaggerHomeComponent
            .factory()
            .create(DaggerCoreComponent.factory().create(requireContext()))
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun initViewModel(): GameViewModel = viewModel

    override fun setupView(view: View) {
        setupGameList()
        viewModel.loadGameList()
        error_layout.try_again_button.setOnClickListener { viewModel.loadGameList() }
    }

    override fun observeViewModel(vm: GameViewModel) {
        vm.errorMessage.observe(viewLifecycleOwner, { showSnackbar(it) })

        vm.showErrorLayout.observe(viewLifecycleOwner, { error ->
            error?.let {
                if (it) binding.errorLayout.show()
                else binding.errorLayout.hide()
            }
        })
    }

    private fun setupGameList() {
        recycler_game.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                EqualSpacingLayout(spacing = 32, mode = EqualSpacingLayout.VERTICAL_MODE)
            )
            setHasFixedSize(true)
            adapter = GameAdapter { v, id ->
                v.findNavController().navigate(HomeFragmentDirections.navHomeToDetail(gameId = id))
            }
        }
    }
}
