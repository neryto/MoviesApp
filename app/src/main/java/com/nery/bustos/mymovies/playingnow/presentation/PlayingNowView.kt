package com.nery.bustos.mymovies.playingnow.presentation

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.nery.bustos.moviesbasemodule.DataState
import com.nery.bustos.moviesbasemodule.presentation.BaseView
import com.nery.bustos.mymovies.App
import com.nery.bustos.mymovies.databinding.PlayingNowFragmentBinding
import com.nery.bustos.mymovies.playingnow.data.PlayingNowItemView
import com.nery.bustos.mymovies.playingnow.presentation.ui.PlayingNowAdapter


class PlayingNowView
    : BaseView<PlayingNowFragmentBinding, PlayingNowActions>() {

    private val viewModel: PlayingNowViewModel =
        ViewModelProvider(this)[PlayingNowViewModel::class.java]

    fun initPlayingNowView(
        lifecycleOwner: LifecycleOwner,
        actionHandler: (action: PlayingNowActions, value: Any?) -> Unit
    ) {
        super.init(lifecycleOwner, actionHandler)
    }

    override fun initObservers() {
        lifecycleOwner.lifecycle.addObserver(this)
        viewModel.fetchInfo.observe(lifecycleOwner,{
            when(it){
                is DataState.Success -> {
                    mBinding.recycler.apply {
                        adapter = PlayingNowAdapter(it.data,onItemClicked)
                        val flexBox  =
                            FlexboxLayoutManager(App.applicationContext(), FlexDirection.COLUMN)
                        layoutManager = flexBox
                    }

                    actionHandler(PlayingNowActions.SHOW_LOTTIE,false)
                }
                is DataState.Error -> {
                    actionHandler(PlayingNowActions.SHOW_LOTTIE,false)
                    actionHandler(PlayingNowActions.SHOW_ERROR,it.message)
                }
                DataState.Loading -> actionHandler(PlayingNowActions.SHOW_LOTTIE,true)
            }
        })
    }

    override fun setupViewBinding(view: View): PlayingNowFragmentBinding {
        binding = PlayingNowFragmentBinding.bind(view)
        return binding as PlayingNowFragmentBinding
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        viewModel.fetchPlayingNow()
    }

    private val onItemClicked : (item: PlayingNowItemView)->Unit={
        actionHandler(PlayingNowActions.SHOW_DETAIL,it)
    }
}