package com.app.zuludin.core.domain.repository

import com.app.zuludin.core.data.Resource
import com.app.zuludin.core.domain.model.Game
import com.app.zuludin.core.domain.model.GameDetail
import com.app.zuludin.core.domain.model.Platform
import com.app.zuludin.core.domain.model.Store
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getGameList(update: Boolean): Flow<Resource<List<Game>>>

    fun getPlatformList(update: Boolean): Flow<Resource<List<Platform>>>

    fun getStoreList(update: Boolean): Flow<Resource<List<Store>>>

    fun getGamesByPlatform(platformId: Int): Flow<Resource<List<Game>>>

    fun getGamesByStore(storeId: Int): Flow<Resource<List<Game>>>

    fun getGameDetail(gameId: Int): Flow<Resource<GameDetail>>

    fun addFavoriteGame(game: GameDetail)

    fun getFavoriteGame(): Flow<List<Game>>

    fun deleteFavoriteGame(gameId: Int)

    fun checkGameFavorite(gameId: Int): Flow<Boolean>
}