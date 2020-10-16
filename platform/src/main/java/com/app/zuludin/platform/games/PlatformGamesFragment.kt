package com.app.zuludin.platform.games

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
import com.app.zuludin.platform.R
import com.app.zuludin.platform.databinding.FragmentPlatformGamesBinding
import com.app.zuludin.platform.di.DaggerPlatformComponent
import kotlinx.android.synthetic.main.error_layout.view.*
import kotlinx.android.synthetic.main.fragment_platform.error_layout
import kotlinx.android.synthetic.main.fragment_platform_games.*
import javax.inject.Inject

class PlatformGamesFragment : BaseFragment<PlatformGamesViewModel>() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: PlatformGamesViewModel by viewModels {
        factory
    }

    private val platformArgs by navArgs<PlatformGamesActivityArgs>()
    private lateinit var binding: FragmentPlatformGamesBinding

    override fun injectClass() {
        DaggerPlatformComponent
            .factory()
            .create(DaggerCoreComponent.factory().create(requireContext()))
            .inject(this)
    }

    override fun initViewModel(): PlatformGamesViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_platform_games, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun setupView(view: View) {
        setupGameList()
        viewModel.loadPlatformGames(platformArgs.platformId)
        error_layout.try_again_button.setOnClickListener { viewModel.loadPlatformGames(platformArgs.platformId) }
    }

    override fun observeViewModel(vm: PlatformGamesViewModel) {
        vm.errorMessage.observe(viewLifecycleOwner, { showSnackbar(it) })

        vm.showErrorLayout.observe(viewLifecycleOwner, {
            it?.let { show ->
                if (show) binding.errorLayout.show()
                else binding.errorLayout.hide()
            }
        })
    }

    private fun setupGameList() {
        recycler_games_platform.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                EqualSpacingLayout(spacing = 32, mode = EqualSpacingLayout.VERTICAL_MODE)
            )
            setHasFixedSize(true)
            adapter = GameAdapter { v, id ->
                v.findNavController()
                    .navigate(PlatformGamesFragmentDirections.navPlatformToDetail(gameId = id))
            }
        }
    }
}