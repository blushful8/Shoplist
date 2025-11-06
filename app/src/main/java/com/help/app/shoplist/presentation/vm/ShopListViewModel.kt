package com.help.app.shoplist.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.help.app.shoplist.domain.HistoryRepository
import com.help.app.shoplist.domain.ShopRepository
import com.help.app.shoplist.domain.model.HistoryItemInfo
import com.help.app.shoplist.domain.model.ShopItemInfo
import com.help.app.shoplist.domain.usecase.AddShopItemUseCase
import com.help.app.shoplist.domain.usecase.RemoveShopItemUseCase
import com.help.app.shoplist.domain.usecase.UpdateShopItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopListViewModel @Inject constructor(
    repository: ShopRepository,
    historyRepository: HistoryRepository,
    private val addShopItemUseCase: AddShopItemUseCase,
    private val removeShopItemUseCase: RemoveShopItemUseCase,
    private val updateShopItemUseCase: UpdateShopItemUseCase
) : ViewModel() {

    val itemsNeedToShop: StateFlow<List<ShopItemInfo>> = repository.getAllShopItems()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(3500),
            initialValue = emptyList()
        )

    val historyItems: StateFlow<List<HistoryItemInfo>> = historyRepository.getAllHistoryItems()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(3500),
            initialValue = emptyList()
        )

    fun addItem(shopItemInfo: ShopItemInfo) {
        viewModelScope.launch {
            addShopItemUseCase.invoke(shopItemInfo)
        }
    }

    fun deleteItem(shopItemInfo: ShopItemInfo) {
        viewModelScope.launch {
            removeShopItemUseCase.invoke(shopItemInfo)
        }
    }

    fun updateItem(shopItemInfo: ShopItemInfo) {
        viewModelScope.launch {
            updateShopItemUseCase.invoke(shopItemInfo)
        }
    }
}