package com.help.app.shoplist.domain.mapper

import com.help.app.shoplist.domain.model.HistoryItemInfo
import com.help.app.shoplist.domain.model.ShopItemInfo
import javax.inject.Inject

class HistoryProductMapper @Inject constructor() {

    fun mapDomainToHistoryModel(itemInfo: ShopItemInfo): HistoryItemInfo {
        return HistoryItemInfo(
            id = 0,
            productName = itemInfo.productName,
            categoryName = itemInfo.categoryName
        )
    }
}