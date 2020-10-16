package com.app.zuludin.core.data.source.remote.network

import com.app.zuludin.core.data.source.remote.response.GameDetailResponse
import com.app.zuludin.core.data.source.remote.response.GameListResponse
import com.app.zuludin.core.data.source.remote.response.GamePlatformResponse
import com.app.zuludin.core.data.source.remote.response.GameStoreResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("User-Agent: Game Over")
    @GET("games")
    suspend fun loadGameList(): GameListResponse

    @Headers("User-Agent: Game Over")
    @GET("platforms")
    suspend fun loadGamePlatform(): GamePlatformResponse

    @Headers("User-Agent: Game Over")
    @GET("stores")
    suspend fun loadGameStore(): GameStoreResponse

    @Headers("User-Agent: Game Over")
    @GET("games")
    suspend fun loadGamesByPlatform(@Query("platforms") platformId: Int): GameListResponse

    @Headers("User-Agent: Game Over")
    @GET("games")
    suspend fun loadGamesByStore(@Query("stores") storeId: Int): GameListResponse

    @Headers("User-Agent: Game Over")
    @GET("games/{id}")
    suspend fun loadGameDetail(@Path("id") id: Int): GameDetailResponse
}