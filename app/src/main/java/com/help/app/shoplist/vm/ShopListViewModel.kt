package com.help.app.shoplist.vm

import androidx.lifecycle.ViewModel
import com.help.app.shoplist.model.ShopItemInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShopListViewModel : ViewModel() {

    private val _itemsNeedToShop = MutableStateFlow(emptyList<ShopItemInfo>())

    val itemsNeedToShop: StateFlow<List<ShopItemInfo>> = _itemsNeedToShop.asStateFlow()

    fun addItem(shopItemInfo: ShopItemInfo) {
        val currentList = _itemsNeedToShop.value
        val newList = (currentList + shopItemInfo).sortedBy { it.category }
        _itemsNeedToShop.value = newList
    }
}