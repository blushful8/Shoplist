package com.help.app.shoplist.domain

import com.help.app.shoplist.domain.model.ShopItemInfo
import kotlinx.coroutines.flow.Flow

interface ShopRepository {
    fun getAllShopItems(): Flow<List<ShopItemInfo>>
    suspend fun addShopItem(item: ShopItemInfo)
    suspend fun deleteShopItem(item: ShopItemInfo)
}