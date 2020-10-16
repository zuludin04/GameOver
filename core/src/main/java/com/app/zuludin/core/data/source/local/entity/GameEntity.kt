package com.app.zuludin.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GameEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "gameId")
    var gameId: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "rating")
    var rating: Double,

    @ColumnInfo(name = "playtime")
    var playtime: Int,

    @ColumnInfo(name = "released")
    var released: String,

    @ColumnInfo(name = "backgroundImage")
    var backgroundImage: String,
)