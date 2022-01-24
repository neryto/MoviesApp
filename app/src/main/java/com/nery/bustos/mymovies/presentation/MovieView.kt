package com.nery.bustos.mymovies.presentation

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.nery.bustos.moviesbasemodule.DataState
import com.nery.bustos.moviesbasemodule.presentation.BaseView
import com.nery.bustos.mymovies.App
import com.nery.bustos.mymovies.databinding.PlayingNowFragmentBinding
import com.nery.bustos.mymovies.presentation.ui.MovieItemView
import com.nery.bustos.mymovies.presentation.ui.MovieAdapter


class MovieView
    : BaseView<PlayingNowFragmentBinding, MovieActions>() {

    private val viewModel: MovieViewModel =
        ViewModelProvider(this)[MovieViewModel::class.java]
    lateinit var type: TypeMovie

    fun initPlayingNowView(
        lifecycleOwner: LifecycleOwner,
        type: TypeMovie,
        actionHandler: (action: MovieActions, value: Any?) -> Unit
    ) {
        this.type = type
        super.init(lifecycleOwner, actionHandler)
    }

    override fun initObservers() {
        lifecycleOwner.lifecycle.addObserver(this)
        viewModel.fetchInfo.observe(lifecycleOwner,{
            when(it){
                is DataState.Success -> {
                    mBinding.recycler.apply {
                        adapter = MovieAdapter(it.data,onItemClicked)
                        val flexBox  =
                            FlexboxLayoutManager(App.applicationContext(), FlexDirection.COLUMN)
                        layoutManager = flexBox
                    }

                    actionHandler(MovieActions.SHOW_LOTTIE,false)
                }
                is DataState.Error -> {
                    actionHandler(MovieActions.SHOW_LOTTIE,false)
                    actionHandler(MovieActions.SHOW_ERROR,it.message)
                }
                DataState.Loading -> actionHandler(MovieActions.SHOW_LOTTIE,true)
            }
        })

        viewModel.fetchVideo.observe(lifecycleOwner,{
            when(it){
                is DataState.Success -> {
                    actionHandler(MovieActions.SHOW_VIDEO_LOADING,false)
                   if (it.data.isEmpty())
                        actionHandler(MovieActions.SHOW_ERROR,"Video not available")
                    else
                        actionHandler(MovieActions.SHOW_VIDEO,it.data.first().key)

                }
                is DataState.Error -> {
                    actionHandler(MovieActions.SHOW_VIDEO_LOADING,false)
                    actionHandler(MovieActions.SHOW_ERROR,it.message)
                }
                DataState.Loading ->
                    actionHandler(MovieActions.SHOW_VIDEO_LOADING,true)
            }
        })
    }

    override fun setupViewBinding(view: View): PlayingNowFragmentBinding {
        binding = PlayingNowFragmentBinding.bind(view)
        return binding as PlayingNowFragmentBinding
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        viewModel.fetchMovie(type)
    }

    fun fetchVideo(id: Int) {
        viewModel.fetchVideo(id)
    }

    private val onItemClicked : (item: MovieItemView)->Unit={
        actionHandler(MovieActions.SHOW_DETAIL,it)
    }
}