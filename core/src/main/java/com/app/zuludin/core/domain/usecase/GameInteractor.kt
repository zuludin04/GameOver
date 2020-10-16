package com.app.zuludin.core.domain.usecase

import com.app.zuludin.core.data.Resource
import com.app.zuludin.core.domain.model.Game
import com.app.zuludin.core.domain.model.GameDetail
import com.app.zuludin.core.domain.model.Platform
import com.app.zuludin.core.domain.model.Store
import com.app.zuludin.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameInteractor @Inject constructor(private val repository: IGameRepository) : GameUseCase {
    override fun getGameList(updaate: Boolean): Flow<Resource<List<Game>>> =
        repository.getGameList(updaate)

    override fun loadPlatformsList(update: Boolean): Flow<Resource<List<Platform>>> = repository.getPlatformList(update)

    override fun loadStoreList(update: Boolean): Flow<Resource<List<Store>>> = repository.getStoreList(update)

    override fun loadGamesByPlatform(platformId: Int): Flow<Resource<List<Game>>> =
        repository.getGamesByPlatform(platformId)

    override fun loadGamesByStore(storeId: Int): Flow<Resource<List<Game>>> =
        repository.getGamesByStore(storeId)

    override fun loadGameDetail(gameId: Int): Flow<Resource<GameDetail>> =
        repository.getGameDetail(gameId)

    override fun addGameFavorite(game: GameDetail) {
        repository.addFavoriteGame(game)
    }

    override fun loadFavoriteGame(): Flow<List<Game>> = repository.getFavoriteGame()

    override fun deleteFavoriteGame(gameId: Int) = repository.deleteFavoriteGame(gameId)

    override fun isGameFavorite(gameId: Int) = repository.checkGameFavorite(gameId)
}