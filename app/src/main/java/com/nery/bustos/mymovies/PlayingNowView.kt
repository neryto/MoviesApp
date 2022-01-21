package com.nery.bustos.mymovies

import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.nery.bustos.moviesbasemodule.presentation.BaseView
import com.nery.bustos.mymovies.databinding.PlayingNowFragmentBinding


class PlayingNowView
    : BaseView<PlayingNowFragmentBinding,PlayingNowActions>() {

    override fun init(lifecycleOwner: LifecycleOwner,
                      actionHandler:(action:PlayingNowActions, value:Any?)->Unit) {

    }

    override fun initObservers() {
        Log.e("TAGGGG","initObservers")
    }

    override fun setupViewBinding(view: View): PlayingNowFragmentBinding {
        binding = PlayingNowFragmentBinding.bind(view)
        return binding as  PlayingNowFragmentBinding
    }
}