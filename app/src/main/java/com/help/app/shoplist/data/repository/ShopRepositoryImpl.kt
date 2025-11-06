package com.help.app.shoplist.data.repository

import com.help.app.shoplist.data.database.ShopItemDao
import com.help.app.shoplist.data.mapper.ShopItemMapper
import com.help.app.shoplist.domain.ShopRepository
import com.help.app.shoplist.domain.model.ShopItemInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    private val shopItemDao: ShopItemDao,
    private val mapper: ShopItemMapper
) :
    ShopRepository {
    override fun getAllShopItems(): Flow<List<ShopItemInfo>> =
        shopItemDao.getAll().map { shopItems ->
            shopItems.map { shopItem ->
                mapper.mapDatabaseModelToDomain(shopItem)
            }
        }

    override suspend fun addShopItem(item: ShopItemInfo) =
        shopItemDao.insert(mapper.mapDomainModelToDatabase(item))

    override suspend fun deleteShopItem(item: ShopItemInfo) =
        shopItemDao.delete(mapper.mapDomainModelToDatabase(item))

    override suspend fun updateShopItem(item: ShopItemInfo) {
        shopItemDao.update(mapper.mapDomainModelToDatabase(item))
    }
}