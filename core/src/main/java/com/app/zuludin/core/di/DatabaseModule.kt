package com.app.zuludin.core.di

import android.content.Context
import androidx.room.Room
import com.app.zuludin.core.data.source.local.room.*
import dagger.Module
import dagger.Provides
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabaseModule(context: Context): GameDatabase {
        val passPhrase: ByteArray = SQLiteDatabase.getBytes("gameover".toCharArray())
        val factory = SupportFactory(passPhrase)
        return Room.databaseBuilder(
            context,
            GameDatabase::class.java,
            "game.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideGameDao(db: GameDatabase): GameDao = db.gameDao()

    @Provides
    fun providePlatformDao(db: GameDatabase): PlatformDao = db.platformDao()

    @Provides
    fun provideStoreDao(db: GameDatabase): StoreDao = db.storeDao()

    @Provides
    fun provideFavoriteDao(db: GameDatabase): FavoriteDao = db.favoriteDao()
}