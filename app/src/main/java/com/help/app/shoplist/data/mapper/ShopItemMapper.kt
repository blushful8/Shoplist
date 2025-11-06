package com.help.app.shoplist.data.mapper

import android.util.Log
import com.help.app.shoplist.data.database.ShopItem
import com.help.app.shoplist.domain.model.ShopItemInfo
import javax.inject.Inject

class ShopItemMapper @Inject constructor() {
    fun mapDatabaseModelToDomain(shopItem: ShopItem): ShopItemInfo {
        return ShopItemInfo(
            id = shopItem.id,
            productName = shopItem.productName,
            productNote = shopItem.productNote,
            categoryName = shopItem.categoryName,
            productIsBought = shopItem.productIsBought
        )
    }

    fun mapDomainModelToDatabase(shopItemInfo: ShopItemInfo): ShopItem {
        Log.d("TAG", "id ${shopItemInfo.id}")
        return ShopItem(
            id = shopItemInfo.id,
            productName = shopItemInfo.productName,
            productNote = shopItemInfo.productNote,
            categoryName = shopItemInfo.categoryName,
            productIsBought = shopItemInfo.productIsBought
        )
    }
}