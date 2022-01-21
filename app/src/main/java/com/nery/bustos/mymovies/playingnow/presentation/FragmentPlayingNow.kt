package com.nery.bustos.mymovies.playingnow.presentation

import android.view.View
import com.nery.bustos.moviesbasemodule.presentation.BaseFragment
import com.nery.bustos.mymovies.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentPlayingNow
    : BaseFragment() {

    companion object {
        fun newInstance() : FragmentPlayingNow = FragmentPlayingNow()
    }

    @Inject lateinit var mView: PlayingNowView

    override fun setLayout(): Int = R.layout.playing_now_fragment

    override fun setupView(view: View) {
        mView.apply {
           setupViewBinding(view)
            initPlayingNowView(this@FragmentPlayingNow,actionHandler)
       }
    }

    private val actionHandler:(action: PlayingNowActions, value:Any?)->Unit = {
            action: PlayingNowActions, value: Any? ->
        when(action){

        }

    }
}