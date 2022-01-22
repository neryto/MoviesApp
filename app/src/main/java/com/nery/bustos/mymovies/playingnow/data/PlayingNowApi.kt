package com.nery.bustos.mymovies.playingnow.data

import com.nery.bustos.moviesbasemodule.network.Api
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayingNowApi {
    @GET("movie/popular")
    suspend fun getPlayingNowList(
        @Query("api_key") ap1k3y: String = Api.AP1K3Y,
        @Query("page") page: Int = 1,
    ):Response<PlayingNowResponse>
}