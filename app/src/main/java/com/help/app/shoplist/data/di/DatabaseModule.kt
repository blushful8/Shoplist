package com.help.app.shoplist.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.help.app.shoplist.data.database.HistoryItemDao
import com.help.app.shoplist.data.database.ShopItemDao
import com.help.app.shoplist.data.database.ShopListDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS product_history_item (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                product_name TEXT NOT NULL,
                category_name TEXT NOT NULL,
                UNIQUE(product_name, category_name)
            )
            """
        )
        database.execSQL(
            "CREATE UNIQUE INDEX IF NOT EXISTS `index_product_history_item_product_name_category_name` ON `product_history_item` (`product_name`, `category_name`)"
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideShopListDatabase(@ApplicationContext context: Context): ShopListDatabase =
        Room.databaseBuilder(
            context,
            ShopListDatabase::class.java,
            "shop_list.db"
        ).addMigrations(MIGRATION_1_2).build()


    @Provides
    @Singleton
    fun provideShopItemDao(database: ShopListDatabase): ShopItemDao = database.shopItemDao()

    @Provides
    @Singleton
    fun provideHistoryItemDao(database: ShopListDatabase): HistoryItemDao = database.historyItemDao()
}