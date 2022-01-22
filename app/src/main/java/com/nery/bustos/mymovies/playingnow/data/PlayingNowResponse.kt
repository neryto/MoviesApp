package com.nery.bustos.mymovies.playingnow.data

import com.google.gson.annotations.SerializedName

data class PlayingNowResponse(
    @SerializedName("page")
    val page : Int,

    @SerializedName("total_pages")
    val totalPages : Int,

    @SerializedName("total_results")
    val totalResults : Int,

    @SerializedName("results")
    val lsPlayingNow : List<PlayingNowItem>

    )