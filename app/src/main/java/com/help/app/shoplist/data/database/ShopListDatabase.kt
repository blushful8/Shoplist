package com.help.app.shoplist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ShopItem::class], version = 1, exportSchema = false)
abstract class ShopListDatabase: RoomDatabase() {
    abstract fun shopItemDao(): ShopItemDao
}