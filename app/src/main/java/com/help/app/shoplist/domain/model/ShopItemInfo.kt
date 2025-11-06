package com.help.app.shoplist.domain.model

data class ShopItemInfo(
    val id: Int = 0,
    val productName: String = "Name",
    val productNote: String = "",
    val categoryName: String = "Category",
    val productIsBought: Boolean = false
)
