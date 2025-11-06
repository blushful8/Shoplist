package com.help.app.shoplist.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryItemDao {
    @Query("SELECT * FROM product_history_item ORDER BY category_name ASC, product_name ASC")
    fun getAll(): Flow<List<ProductHistoryItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(historyItem: ProductHistoryItem)
}