package com.help.app.shoplist.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ShopItemDao {
    @Query("SELECT * FROM shop_item ORDER BY category_name ASC, product_name ASC")
    fun getAll(): Flow<List<ShopItem>>

    @Insert
    suspend fun insert(shopItem: ShopItem)

    @Delete
    suspend fun delete(shopItem: ShopItem)
}