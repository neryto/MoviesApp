package com.nery.bustos.mymovies

import android.os.Bundle
import com.nery.bustos.moviesbasemodule.presentation.BaseActivity

class MainActivity : BaseActivity() {

    private val idContainer = R.id.main_container
    private val tagFragmentPlayingNow = FragmentPlayingNow::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(idContainer,FragmentPlayingNow.newInstance(),tagFragmentPlayingNow)
    }


}