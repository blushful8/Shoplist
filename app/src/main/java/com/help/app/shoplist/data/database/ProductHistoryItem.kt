package com.help.app.shoplist.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "product_history_item",
    indices = [Index(value = ["product_name", "category_name"], unique = true)]
)

data class ProductHistoryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "product_name") val productName: String,
    @ColumnInfo(name = "category_name") val categoryName: String,
)
