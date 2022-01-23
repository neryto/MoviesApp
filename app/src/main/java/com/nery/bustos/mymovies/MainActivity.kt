package com.nery.bustos.mymovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nery.bustos.moviesbasemodule.network.Api
import com.nery.bustos.moviesbasemodule.presentation.BaseActivity
import com.nery.bustos.mymovies.databinding.ItemDetailBinding
import com.nery.bustos.mymovies.playingnow.data.PlayingNowItemView
import com.nery.bustos.mymovies.playingnow.presentation.ui.FragmentPlayingNow
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

    override fun showDetail(item: PlayingNowItemView) {
         BottomSheetDialog(this).apply {
            val view : View = layoutInflater
                .inflate(R.layout.item_detail,null,false)
            val binding = ItemDetailBinding.bind(view).apply {
                overview.text = item.overview
                Glide.with(this.root).load("${Api.IMAGE_URL}${item.backdropPath}")
                    .into(banner)
            }
            setCancelable(true)
            setContentView(binding.root)
            show()
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