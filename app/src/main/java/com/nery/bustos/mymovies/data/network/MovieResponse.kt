package com.nery.bustos.mymovies.data.network

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    val page : Int,

    @SerializedName("total_pages")
    val totalPages : Int,

    @SerializedName("total_results")
    val totalResults : Int,

    @SerializedName("results")
    val lsPlayingNow : List<MovieItem>

    )