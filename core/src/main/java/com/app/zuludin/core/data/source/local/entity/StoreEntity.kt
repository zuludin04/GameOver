package com.app.zuludin.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "store")
data class StoreEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "storeId")
    val id: Int?,

    @ColumnInfo(name = "storeName")
    val name: String?,

    @ColumnInfo(name = "storeImage")
    val image: String?,

    @ColumnInfo(name = "storeCount")
    val count: Int?
)