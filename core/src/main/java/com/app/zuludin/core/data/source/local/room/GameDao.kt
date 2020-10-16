package com.app.zuludin.core.data.source.local.room

import androidx.room.*
import com.app.zuludin.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM game")
    fun getAllGame() : Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGameCache(games: List<GameEntity>)
}