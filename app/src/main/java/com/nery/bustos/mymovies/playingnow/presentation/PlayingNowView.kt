package com.nery.bustos.mymovies.playingnow.presentation

import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.nery.bustos.moviesbasemodule.DataState
import com.nery.bustos.moviesbasemodule.presentation.BaseView
import com.nery.bustos.mymovies.databinding.PlayingNowFragmentBinding


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
                    Log.e("TAG","Success ${it.data.size}")

                }
                is DataState.Error -> {
                    Log.e("TAG","Error ${it.message}")
                }
                DataState.Loading -> {
                    Log.e("TAG","Loading")
                }
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
}