package com.nery.bustos.mymovies.playingnow.data

import com.google.gson.annotations.SerializedName

data class PlayingNowItem(

    @SerializedName("adult")
    val isAdult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("video")
    val video: Boolean,

    @SerializedName("vote_average")
    val voteAverage: Double,

    ) {
    fun toItemView(): PlayingNowItemView{
        return PlayingNowItemView(
            this.id,
            this.overview,
            this.posterPath,
            this.backdropPath,
            this.releaseDate,
            this.title,
            this.video,
            this.voteAverage
        )
    }
}