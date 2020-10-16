package com.app.zuludin.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.zuludin.core.data.source.local.entity.FavoriteEntity
import com.app.zuludin.core.data.source.local.entity.GameEntity
import com.app.zuludin.core.data.source.local.entity.PlatformEntity
import com.app.zuludin.core.data.source.local.entity.StoreEntity

@Database(entities = [GameEntity::class, PlatformEntity::class, StoreEntity::class, FavoriteEntity::class], version = 1, exportSchema = false)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao

    abstract fun platformDao(): PlatformDao

    abstract fun storeDao(): StoreDao

    abstract fun favoriteDao(): FavoriteDao
}