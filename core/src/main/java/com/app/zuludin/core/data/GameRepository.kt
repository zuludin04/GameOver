package com.app.zuludin.core.data

import com.app.zuludin.core.data.source.local.LocalDataSource
import com.app.zuludin.core.data.source.remote.RemoteDataSource
import com.app.zuludin.core.data.source.remote.network.ApiResponse
import com.app.zuludin.core.data.source.remote.response.ResultsItem
import com.app.zuludin.core.data.source.remote.response.ResultsPlatform
import com.app.zuludin.core.data.source.remote.response.ResultsStore
import com.app.zuludin.core.domain.model.Game
import com.app.zuludin.core.domain.model.GameDetail
import com.app.zuludin.core.domain.model.Platform
import com.app.zuludin.core.domain.model.Store
import com.app.zuludin.core.domain.repository.IGameRepository
import com.app.zuludin.core.utils.AppExecutors
import com.app.zuludin.core.utils.DataMapper
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGameRepository {
    override fun getGameList(update: Boolean): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<ResultsItem>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGame().map {
                    DataMapper.mapGameEntitiesToDomains(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean =
                data == null || data.isEmpty() || update

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getGameList()

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val gameList = DataMapper.mapGameResponsesToEntities(data)
                localDataSource.insertFavoriteGame(gameList)
            }
        }.asFlow()

    override fun getPlatformList(update: Boolean): Flow<Resource<List<Platform>>> =
        object : NetworkBoundResource<List<Platform>, List<ResultsPlatform>>() {
            override fun loadFromDB(): Flow<List<Platform>> {
                return localDataSource.getGamePlatforms().map {
                    DataMapper.mapPlatformEntitiesToDomains(it)
                }
            }

            override fun shouldFetch(data: List<Platform>?): Boolean =
                data == null || data.isEmpty() || update

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsPlatform>>> =
                remoteDataSource.getGamePlatform()

            override suspend fun saveCallResult(data: List<ResultsPlatform>) {
                val platforms = DataMapper.mapPlatformResponsesToEntities(data)
                localDataSource.insertPlatformsCached(platforms)
            }
        }.asFlow()

    override fun getStoreList(update: Boolean): Flow<Resource<List<Store>>> =
        object : NetworkBoundResource<List<Store>, List<ResultsStore>>() {
            override fun loadFromDB(): Flow<List<Store>> {
                return localDataSource.getGameStores().map {
                    DataMapper.mapStoreEntitiesToDomains(it)
                }
            }

            override fun shouldFetch(data: List<Store>?): Boolean =
                data == null || data.isEmpty() || update

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsStore>>> =
                remoteDataSource.getGameStores()

            override suspend fun saveCallResult(data: List<ResultsStore>) {
                val stores = DataMapper.mapStoreResponsesToEntities(data)
                localDataSource.insertStoreCache(stores)
            }
        }.asFlow()

    override fun getGamesByPlatform(platformId: Int): Flow<Resource<List<Game>>> {
        return flow {
            val apiResponse = remoteDataSource.getGamesByPlatform(platformId).first()

            if (apiResponse is ApiResponse.Success) {
                val games = apiResponse.data
                val result = Resource.Success(DataMapper.mapGameResponsesToDomains(games))
                emitAll(flowOf(result))
            } else if (apiResponse is ApiResponse.Error) {
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun getGamesByStore(storeId: Int): Flow<Resource<List<Game>>> {
        return flow {
            val apiResponse = remoteDataSource.getGamesByStore(storeId).first()

            if (apiResponse is ApiResponse.Success) {
                val games = apiResponse.data
                val result = Resource.Success(DataMapper.mapGameResponsesToDomains(games))
                emitAll(flowOf(result))
            } else if (apiResponse is ApiResponse.Error) {
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun getGameDetail(gameId: Int): Flow<Resource<GameDetail>> {
        return flow {
            val apiResponse = remoteDataSource.getGameDetail(gameId).first()

            if (apiResponse is ApiResponse.Success) {
                val detail = apiResponse.data
                emitAll(flowOf(Resource.Success(DataMapper.mapGameDetailResponseToDomain(detail))))
            } else if (apiResponse is ApiResponse.Error) {
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun addFavoriteGame(game: GameDetail) {
        val gameEntity = DataMapper.mapDetailDomainToFavoriteEntity(game)
        appExecutors.diskIO().execute {
            localDataSource.addGameFavorite(gameEntity)
        }
    }

    override fun getFavoriteGame(): Flow<List<Game>> {
        return localDataSource.getFavoriteGame().map {
            DataMapper.mapFavoriteEntityToGameDomain(it)
        }
    }

    override fun deleteFavoriteGame(gameId: Int) {
        appExecutors.diskIO().execute {
            localDataSource.deleteGameFavorite(gameId)
        }
    }

    override fun checkGameFavorite(gameId: Int): Flow<Boolean> {
        return flow {
            emit(localDataSource.gameIsFavorite(gameId))
        }
    }
}