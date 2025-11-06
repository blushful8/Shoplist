package com.help.app.shoplist.data.di

import android.content.Context
import androidx.room.Room
import com.help.app.shoplist.data.database.ShopItemDao
import com.help.app.shoplist.data.database.ShopListDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

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
        ).build()


    @Provides
    @Singleton
    fun provideShopItemDao(database: ShopListDatabase): ShopItemDao = database.shopItemDao()

}