package com.app.zuludin.gameover.favorite

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
import com.app.zuludin.gameover.favorite.databinding.FragmentFavoriteListBinding
import com.app.zuludin.gameover.favorite.di.DaggerFavoriteComponent
import kotlinx.android.synthetic.main.fragment_favorite_list.*
import javax.inject.Inject

class FavoriteListFragment : BaseFragment<FavoriteListViewModel>() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel by viewModels<FavoriteListViewModel> {
        factory
    }

    private lateinit var binding: FragmentFavoriteListBinding

    override fun injectClass() {
        DaggerFavoriteComponent
            .factory()
            .create(DaggerCoreComponent.factory().create(requireContext()))
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_list, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun initViewModel(): FavoriteListViewModel = viewModel

    override fun setupView(view: View) {
        setupFavoriteList()
        viewModel.loadFavoriteGames()
    }

    override fun observeViewModel(vm: FavoriteListViewModel) {}

    private fun setupFavoriteList() {
        recycler_favorite.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                EqualSpacingLayout(spacing = 16, mode = EqualSpacingLayout.VERTICAL_MODE)
            )
            setHasFixedSize(true)
            adapter = GameAdapter { v, id ->
                v.findNavController()
                    .navigate(FavoriteListFragmentDirections.navFavoriteToDetail(id))
            }
        }
    }
}