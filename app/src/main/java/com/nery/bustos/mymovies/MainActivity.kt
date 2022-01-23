package com.nery.bustos.mymovies

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.airbnb.lottie.LottieAnimationView
import com.nery.bustos.moviesbasemodule.presentation.BaseActivity
import com.nery.bustos.mymovies.playingnow.presentation.ui.FragmentPlayingNow
import com.nery.bustos.mymovies.playingnow.presentation.ui.VideoActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(), FragmentPlayingNow.FragmentPlayingNowHandler {

    private val idContainer = R.id.main_container
    private val tagFragmentPlayingNow = FragmentPlayingNow::class.java.name

    lateinit var lottie: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lottie = findViewById(R.id.lottie)
        addFragment(idContainer, FragmentPlayingNow
            .newInstance(this), tagFragmentPlayingNow)
    }

    override fun showVideo(video: String) {
        Intent(this,VideoActivity::class.java).apply {
            putExtra("video",video)
            startActivity(this)
        }
    }


    override fun showLottie(show: Boolean) {
        with(lottie) {
            visibility = if (show) View.VISIBLE else View.GONE
            if (show)
                playAnimation()
             else
                clearAnimation()
        }


    }


}