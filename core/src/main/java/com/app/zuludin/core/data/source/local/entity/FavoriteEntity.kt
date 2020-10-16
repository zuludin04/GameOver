package com.app.zuludin.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "gameId")
    var gameId: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "rating")
    var rating: Double,

    @ColumnInfo(name = "released")
    var released: String,

    @ColumnInfo(name = "backgroundImage")
    var backgroundImage: String
)