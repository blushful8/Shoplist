package com.help.app.shoplist.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.help.app.shoplist.domain.ShopRepository
import com.help.app.shoplist.domain.model.ShopItemInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopListViewModel @Inject constructor(private val repository: ShopRepository) : ViewModel() {

    val itemsNeedToShop: StateFlow<List<ShopItemInfo>> = repository.getAllShopItems()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(3500),
            initialValue = emptyList()
        )

    fun addItem(shopItemInfo: ShopItemInfo) {
        viewModelScope.launch {
            repository.addShopItem(shopItemInfo)
        }
    }

    fun deleteItem(shopItemInfo: ShopItemInfo) {
        viewModelScope.launch {
            repository.deleteShopItem(shopItemInfo)
        }
    }
}