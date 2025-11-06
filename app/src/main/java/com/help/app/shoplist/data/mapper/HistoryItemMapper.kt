package com.help.app.shoplist.data.mapper

import com.help.app.shoplist.data.database.ProductHistoryItem
import com.help.app.shoplist.domain.model.HistoryItemInfo
import javax.inject.Inject

class HistoryItemMapper @Inject constructor() {
    fun mapDatabaseModelToDomain(historyItem: ProductHistoryItem): HistoryItemInfo {
        return HistoryItemInfo(
            id = historyItem.id,
            productName = historyItem.productName,
            categoryName = historyItem.categoryName,
        )
    }

    fun mapDomainModelToDatabase(historyItemInfo: HistoryItemInfo): ProductHistoryItem {
        return ProductHistoryItem(
            id = historyItemInfo.id,
            productName = historyItemInfo.productName,
            categoryName = historyItemInfo.categoryName,
        )
    }
}