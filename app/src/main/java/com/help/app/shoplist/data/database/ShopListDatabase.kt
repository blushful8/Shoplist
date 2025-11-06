package com.help.app.shoplist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ShopItem::class, ProductHistoryItem::class], version = 2, exportSchema = false)
abstract class ShopListDatabase: RoomDatabase() {
    abstract fun shopItemDao(): ShopItemDao
    abstract fun historyItemDao(): HistoryItemDao
}