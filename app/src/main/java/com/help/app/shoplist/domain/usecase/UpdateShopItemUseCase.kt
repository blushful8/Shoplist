package com.help.app.shoplist.domain.usecase

import com.help.app.shoplist.domain.ShopRepository
import com.help.app.shoplist.domain.model.ShopItemInfo
import javax.inject.Inject

class UpdateShopItemUseCase @Inject constructor(private val repository: ShopRepository) {
    suspend fun invoke(item: ShopItemInfo) {
        repository.updateShopItem(item)
    }
}