package com.nery.bustos.mymovies.data.network

import com.google.gson.annotations.SerializedName
import com.nery.bustos.mymovies.presentation.ui.MovieItemView
import com.nery.bustos.mymovies.data.db.MovieEntity

data class MovieItem(

    @SerializedName("adult")
    val isAdult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

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
    fun toItemView(): MovieItemView = MovieItemView(
        this.id,
        this.overview,
        this.posterPath,
        this.backdropPath ?: "",
        this.releaseDate,
        this.title,
        this.video,
        this.voteAverage
    )

    fun toEntity(type: Int): MovieEntity = MovieEntity(
        this.id,
        type,
        this.isAdult,
        this.backdropPath,
        this.originalLanguage,
        this.originalTitle,
        this.overview,
        this.posterPath,
        this.releaseDate,
        this.title,
        this.video,
        this.voteAverage
    )
}
