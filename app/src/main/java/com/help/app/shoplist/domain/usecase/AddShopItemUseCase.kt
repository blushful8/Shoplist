package com.help.app.shoplist.domain.usecase

import com.help.app.shoplist.domain.HistoryRepository
import com.help.app.shoplist.domain.ShopRepository
import com.help.app.shoplist.domain.mapper.HistoryProductMapper
import com.help.app.shoplist.domain.model.ShopItemInfo
import javax.inject.Inject

class AddShopItemUseCase @Inject constructor(
    private val shopRepository: ShopRepository,
    private val historyRepository: HistoryRepository,
    private val historyProductMapper: HistoryProductMapper
) {
    suspend operator fun invoke(item: ShopItemInfo) {
        shopRepository.addShopItem(item)
        historyRepository.addHistoryItem(historyProductMapper.mapDomainToHistoryModel(item))
    }
}