package com.nery.bustos.mymovies.playingnow.data

import com.google.gson.annotations.SerializedName

data class PlayingNowItemView(
    val id: Int,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    )