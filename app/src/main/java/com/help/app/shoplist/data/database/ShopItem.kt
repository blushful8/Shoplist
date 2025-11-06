package com.help.app.shoplist.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("shop_item")
data class ShopItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "product_name") val productName: String,
    @ColumnInfo(name = "product_note") val productNote: String,
    @ColumnInfo(name = "category_name") val categoryName: String,
    @ColumnInfo(name = "product_is_bought") val productIsBought: Boolean
)