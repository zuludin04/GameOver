package com.app.zuludin.core.data.source.local

import com.app.zuludin.core.data.source.local.entity.FavoriteEntity
import com.app.zuludin.core.data.source.local.entity.GameEntity
import com.app.zuludin.core.data.source.local.entity.PlatformEntity
import com.app.zuludin.core.data.source.local.entity.StoreEntity
import com.app.zuludin.core.data.source.local.room.FavoriteDao
import com.app.zuludin.core.data.source.local.room.GameDao
import com.app.zuludin.core.data.source.local.room.PlatformDao
import com.app.zuludin.core.data.source.local.room.StoreDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val gameDao: GameDao,
    private val platformDao: PlatformDao,
    private val storeDao: StoreDao,
    private val favoriteDao: FavoriteDao
) {
    fun getAllGame() = gameDao.getAllGame()

    fun getGamePlatforms() = platformDao.loadPlatformFromDb()

    fun getGameStores() = storeDao.loadStoreCached()

    suspend fun insertStoreCache(stores: List<StoreEntity>) = storeDao.saveStoreCached(stores)

    suspend fun insertFavoriteGame(gameList: List<GameEntity>) = gameDao.addGameCache(gameList)

    suspend fun insertPlatformsCached(platforms: List<PlatformEntity>) =
        platformDao.insertPlatformCache(platforms)

    fun getFavoriteGame() = favoriteDao.getGameFavorite()

    fun addGameFavorite(favoriteEntity: FavoriteEntity) =
        favoriteDao.addGameFavorite(favoriteEntity)

    fun deleteGameFavorite(id: Int) = favoriteDao.deleteGameFavorite(id)

    suspend fun gameIsFavorite(id: Int): Boolean = favoriteDao.checkGameFavorite(id) == 1
}