package com.nery.bustos.mymovies.playingnow.presentation.ui

import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nery.bustos.moviesbasemodule.network.Api
import com.nery.bustos.moviesbasemodule.presentation.BaseFragment
import com.nery.bustos.mymovies.R
import com.nery.bustos.mymovies.databinding.ItemDetailBinding
import com.nery.bustos.mymovies.playingnow.data.PlayingNowItemView
import com.nery.bustos.mymovies.playingnow.presentation.FragmentHandler
import com.nery.bustos.mymovies.playingnow.presentation.PlayingNowActions
import com.nery.bustos.mymovies.playingnow.presentation.PlayingNowView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentPlayingNow
    : BaseFragment() {

    companion object {
        fun newInstance(handler: FragmentPlayingNowHandler): FragmentPlayingNow =
            FragmentPlayingNow().apply {
                this.handler = handler
            }
    }

    interface FragmentPlayingNowHandler : FragmentHandler {
        fun showVideo(video: String)
    }

    @Inject
    lateinit var mView: PlayingNowView
    lateinit var handler: FragmentPlayingNowHandler

    override fun setLayout(): Int = R.layout.playing_now_fragment

    override fun setupView(view: View) {
        mView.apply {
            setupViewBinding(view)
            initPlayingNowView(this@FragmentPlayingNow, actionHandler)
        }
    }

    private val actionHandler: (action: PlayingNowActions, value: Any?) -> Unit =
        { action: PlayingNowActions, value: Any? ->
            when (action) {
                PlayingNowActions.SHOW_LOTTIE ->
                    handler.showLottie(value as Boolean)
                PlayingNowActions.SHOW_ERROR ->
                    Toast.makeText(requireContext(), value as String, Toast.LENGTH_LONG).show()
                PlayingNowActions.SHOW_DETAIL -> showDetail(value as PlayingNowItemView)
                PlayingNowActions.SHOW_VIDEO_LOADING -> handler.showLottie(value as Boolean)
                PlayingNowActions.SHOW_VIDEO -> handler.showVideo(value as String)
            }

        }

    private fun showDetail(item: PlayingNowItemView) {
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