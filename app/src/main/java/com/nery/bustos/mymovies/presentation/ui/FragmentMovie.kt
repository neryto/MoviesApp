package com.nery.bustos.mymovies.presentation.ui

import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nery.bustos.moviesbasemodule.network.Api
import com.nery.bustos.moviesbasemodule.presentation.BaseFragment
import com.nery.bustos.mymovies.R
import com.nery.bustos.mymovies.data.MovieItemView
import com.nery.bustos.mymovies.databinding.ItemDetailBinding
import com.nery.bustos.mymovies.presentation.FragmentHandler
import com.nery.bustos.mymovies.presentation.MovieActions
import com.nery.bustos.mymovies.presentation.MovieView
import com.nery.bustos.mymovies.presentation.TypeMovie
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentMovie
    : BaseFragment() {

    companion object {
        fun newInstance(
            handler: FragmentPlayingNowHandler,
            type: TypeMovie
        ): FragmentMovie =
            FragmentMovie().apply {
                this.handler = handler
                this.typeMovie = type
            }
    }

    interface FragmentPlayingNowHandler : FragmentHandler {
        fun showVideo(video: String)
    }

    @Inject
    lateinit var mView: MovieView
    lateinit var handler: FragmentPlayingNowHandler
    lateinit var typeMovie: TypeMovie

    override fun setLayout(): Int = R.layout.playing_now_fragment

    override fun setupView(view: View) {
        mView.apply {
            setupViewBinding(view)
            initPlayingNowView(this@FragmentMovie, typeMovie, actionHandler)
        }
    }

    private val actionHandler: (action: MovieActions, value: Any?) -> Unit =
        { action: MovieActions, value: Any? ->
            when (action) {
                MovieActions.SHOW_LOTTIE ->
                    handler.showLottie(value as Boolean)
                MovieActions.SHOW_ERROR ->
                    Toast.makeText(requireContext(), value as String, Toast.LENGTH_LONG).show()
                MovieActions.SHOW_DETAIL -> showDetail(value as MovieItemView)
                MovieActions.SHOW_VIDEO_LOADING -> handler.showLottie(value as Boolean)
                MovieActions.SHOW_VIDEO -> handler.showVideo(value as String)
            }

        }

    private fun showDetail(item: MovieItemView) {
        BottomSheetDialog(requireContext()).apply {
            val view: View = layoutInflater
                .inflate(R.layout.item_detail, null, false)
            val binding = ItemDetailBinding.bind(view).apply {
                overview.text = item.overview
                Glide.with(this.root).load("${Api.IMAGE_URL}${item.backdropPath}")
                    .into(banner)
                watchVideo.setOnClickListener {
                    mView.fetchVideo(item.id)
                }
            }
            setCancelable(true)
            setContentView(binding.root)
            show()
        }
    }
}