package com.nery.bustos.mymovies.data.network

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("results")
    val lsVideos: List<VideoItem>,

    )