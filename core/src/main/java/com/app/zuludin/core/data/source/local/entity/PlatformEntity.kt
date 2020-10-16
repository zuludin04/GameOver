package com.app.zuludin.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "platform")
data class PlatformEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "platformId")
    var id: Int?,

    @ColumnInfo(name = "platformName")
    var name: String?,

    @ColumnInfo(name = "platformImage")
    var image: String?,

    @ColumnInfo(name= "platformCount")
    var count: Int?
)