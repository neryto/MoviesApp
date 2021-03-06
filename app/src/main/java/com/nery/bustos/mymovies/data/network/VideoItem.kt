package com.nery.bustos.mymovies.data.network

import com.google.gson.annotations.SerializedName
import com.nery.bustos.mymovies.presentation.ui.VideoItemView

data class VideoItem(
    @SerializedName("iso_3166_1")
    val iso31661:String,

    @SerializedName("iso_639_1")
    val iso6391:String,

    @SerializedName("key")
    val key:String,

    @SerializedName("name")
    val name:String,

    @SerializedName("official")
    val official:Boolean,

    @SerializedName("published_at")
    val publishedAt:String,

    @SerializedName("site")
    val site:String,

    @SerializedName("size")
    val size:Int,

    @SerializedName("type")
    val type:String,


){
    fun toVideoItemView() : VideoItemView = VideoItemView(
        this.key
    )
}
