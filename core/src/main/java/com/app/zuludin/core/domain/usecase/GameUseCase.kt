package com.app.zuludin.core.domain.usecase

import com.app.zuludin.core.data.Resource
import com.app.zuludin.core.domain.model.Game
import com.app.zuludin.core.domain.model.GameDetail
import com.app.zuludin.core.domain.model.Platform
import com.app.zuludin.core.domain.model.Store
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getGameList(update: Boolean): Flow<Resource<List<Game>>>

    fun loadPlatformsList(update: Boolean): Flow<Resource<List<Platform>>>

    fun loadStoreList(update: Boolean): Flow<Resource<List<Store>>>

    fun loadGamesByPlatform(platformId: Int): Flow<Resource<List<Game>>>

    fun loadGamesByStore(storeId: Int): Flow<Resource<List<Game>>>

    fun loadGameDetail(gameId: Int): Flow<Resource<GameDetail>>

    fun addGameFavorite(game: GameDetail)

    fun loadFavoriteGame(): Flow<List<Game>>

    fun deleteFavoriteGame(gameId: Int)

    fun isGameFavorite(gameId: Int): Flow<Boolean>
}