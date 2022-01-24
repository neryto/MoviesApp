package com.nery.bustos.mymovies

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nery.bustos.moviesbasemodule.presentation.BaseActivity
import com.nery.bustos.mymovies.presentation.TypeMovie
import com.nery.bustos.mymovies.presentation.ui.FragmentMovie
import com.nery.bustos.mymovies.presentation.ui.VideoActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(), FragmentMovie.FragmentPlayingNowHandler {

    private val idContainer = R.id.main_container
    private val tagFragmentPlayingNow = FragmentMovie::class.java.name

    lateinit var lottie: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lottie = findViewById(R.id.lottie)
        val navigationView: BottomNavigationView =
            findViewById(R.id.bottom_navigation)

        addFragment(
            idContainer, FragmentMovie
                .newInstance(
                    this,
                    TypeMovie.PLAYING_NOW
                ), tagFragmentPlayingNow
        )

        navigationView
            .setOnItemSelectedListener { item ->
                removeCurrentFragment()
                when (item.itemId) {
                    R.id.page_1 -> {
                        addFragment(
                            idContainer, FragmentMovie
                                .newInstance(
                                    this,
                                    TypeMovie.PLAYING_NOW
                                ), tagFragmentPlayingNow
                        )
                    }
                    R.id.page_2 -> {
                        addFragment(
                            idContainer, FragmentMovie
                                .newInstance(
                                    this,
                                    TypeMovie.MOST_POPULAR
                                ), tagFragmentPlayingNow
                        )
                    }

                }
                true
            }


    }

    private fun removeCurrentFragment() {
        getCurrentFragment(idContainer)?.let {
            supportFragmentManager.beginTransaction().remove(
                it
            ).commitNow()
        }

    }

    override fun showVideo(video: String) {
        Intent(this, VideoActivity::class.java).apply {
            putExtra("video", video)
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