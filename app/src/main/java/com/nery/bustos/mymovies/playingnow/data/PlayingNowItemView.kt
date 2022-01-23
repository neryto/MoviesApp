package com.nery.bustos.mymovies.playingnow.data

data class PlayingNowItemView(
    val id: Int,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    )