package com.nery.bustos.mymovies.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nery.bustos.mymovies.presentation.ui.MovieItemView

@Entity
data class MovieEntity(

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "type")
    val type: Int,

    @ColumnInfo(name = "adult")
    val isAdult: Boolean,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,

    @ColumnInfo(name = "original_language")
    val originalLanguage: String,

    @ColumnInfo(name = "original_title")
    val originalTitle: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "video")
    val video: Boolean,

    @ColumnInfo(name = "vote_average")
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
}