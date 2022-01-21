package com.nery.bustos.mymovies

import android.view.View
import com.nery.bustos.moviesbasemodule.presentation.BaseFragment


class FragmentPlayingNow
    : BaseFragment() {

    companion object {
        fun newInstance() : FragmentPlayingNow = FragmentPlayingNow()
    }


    override fun setLayout(): Int = R.layout.playing_now_fragment

    override fun setupView(view: View) {
        /*mView.apply {
           setupViewBinding(view)
           init(this@FragmentPlayingNow,actionHandler)
       }*/
    }

    private val actionHandler:(action:PlayingNowActions, value:Any?)->Unit = {
            action: PlayingNowActions, value: Any? ->
        when(action){

        }

    }
}