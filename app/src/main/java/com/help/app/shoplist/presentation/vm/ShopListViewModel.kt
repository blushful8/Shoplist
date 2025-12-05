package com.help.app.shoplist.presentation.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.help.app.shoplist.domain.HistoryRepository
import com.help.app.shoplist.domain.ShopRepository
import com.help.app.shoplist.domain.model.HistoryItemInfo
import com.help.app.shoplist.domain.model.NewShopItemDetails
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
    private val savedStateHandle: SavedStateHandle,
    repository: ShopRepository,
    historyRepository: HistoryRepository,
    private val addShopItemUseCase: AddShopItemUseCase,
    private val removeShopItemUseCase: RemoveShopItemUseCase,
    private val updateShopItemUseCase: UpdateShopItemUseCase
) : ViewModel() {
    companion object {
        private const val PRODUCT_KEY = "product_details"
        private const val CATEGORY_KEY = "category_details"
    }

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

    private val _lastProductDetails =
        savedStateHandle.getStateFlow(PRODUCT_KEY, NewShopItemDetails("", ""))


    private val _lastCategoryDetails =
        savedStateHandle.getStateFlow(CATEGORY_KEY, NewShopItemDetails("", ""))


    fun inputProductDetails(details: NewShopItemDetails) {
        savedStateHandle[PRODUCT_KEY] = details
    }

    fun inputCategoryDetails(details: NewShopItemDetails) {
        savedStateHandle[CATEGORY_KEY] = details
    }

    fun addItem() {
        viewModelScope.launch {
            val productDetails = _lastProductDetails.value
            val categoryDetails = _lastCategoryDetails.value


            addShopItemUseCase.invoke(
                item = ShopItemInfo(
                    id = 0,
                    productName = productDetails.itemName,
                    categoryName = categoryDetails.itemName,
                    productNote = productDetails.itemDescription
                )
            )
        }
    }

    fun deleteItem(shopItemInfo: ShopItemInfo) {
        viewModelScope.launch {
            removeShopItemUseCase.invoke(item = shopItemInfo)
        }
    }

    fun updateItem(shopItemInfo: ShopItemInfo) {
        viewModelScope.launch {
            updateShopItemUseCase.invoke(item = shopItemInfo)
        }
    }
}