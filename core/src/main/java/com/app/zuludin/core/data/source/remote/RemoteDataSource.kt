package com.app.zuludin.core.data.source.remote

import android.util.Log
import com.app.zuludin.core.data.source.remote.network.ApiResponse
import com.app.zuludin.core.data.source.remote.network.ApiService
import com.app.zuludin.core.data.source.remote.response.GameDetailResponse
import com.app.zuludin.core.data.source.remote.response.ResultsItem
import com.app.zuludin.core.data.source.remote.response.ResultsPlatform
import com.app.zuludin.core.data.source.remote.response.ResultsStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getGameList(): Flow<ApiResponse<List<ResultsItem>>> {
        return flow {
            try {
                val response = apiService.loadGameList()
                val games = response.results!!
                if (games.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGamePlatform(): Flow<ApiResponse<List<ResultsPlatform>>> {
        return flow {
            try {
                val response = apiService.loadGamePlatform()
                val platforms = response.results!!
                if (platforms.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGameStores(): Flow<ApiResponse<List<ResultsStore>>> {
        return flow {
            try {
                val response = apiService.loadGameStore()
                val stores = response.results!!
                if (stores.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGamesByPlatform(platformId: Int): Flow<ApiResponse<List<ResultsItem>>> {
        return flow {
            try {
                val response = apiService.loadGamesByPlatform(platformId)
                val games = response.results!!
                if (games.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGamesByStore(storeId: Int): Flow<ApiResponse<List<ResultsItem>>> {
        return flow {
            try {
                val response = apiService.loadGamesByStore(storeId)
                val games = response.results!!
                if (games.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGameDetail(gameId: Int): Flow<ApiResponse<GameDetailResponse>> {
        Log.d("ResourceViewModel", "Load Resource Detail")
        return flow {
            try {
                val response = apiService.loadGameDetail(gameId)
                Log.d("Detail Response", response.name.toString())
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}