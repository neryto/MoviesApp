package com.nery.bustos.mymovies.data.network

import com.nery.bustos.moviesbasemodule.network.Api
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/{type}")
    suspend fun getMovieList(
        @Path("type") type: String,
        @Query("api_key") ap1k3y: String = Api.AP1K3Y,
        @Query("page") page: Int = 1,
    ): Response<MovieResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getVideo(
        @Path("movie_id") id: Int,
        @Query("api_key") ap1k3y: String = Api.AP1K3Y

    ): Response<VideoResponse>

}