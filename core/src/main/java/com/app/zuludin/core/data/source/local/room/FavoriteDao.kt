package com.app.zuludin.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.zuludin.core.data.source.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    fun getGameFavorite(): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGameFavorite(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM favorite WHERE gameId = :id")
    fun deleteGameFavorite(id: Int)

    @Query("SELECT COUNT(name) FROM favorite WHERE gameId = :id")
    suspend fun checkGameFavorite(id: Int): Int
}