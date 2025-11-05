package com.help.app.shoplist.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.help.app.shoplist.R
import com.help.app.shoplist.dialog.InputProductNameDialog
import com.help.app.shoplist.dialog.ProductCategoryChooserDialog
import com.help.app.shoplist.model.ShopItemInfo
import com.help.app.shoplist.shop.ShopAddButton
import com.help.app.shoplist.shop.ShopItem

@Composable
fun ShopScreen(
    modifier: Modifier = Modifier, shopItemInfos: List<ShopItemInfo> = listOf(
        ShopItemInfo()
    ),
    onAddNewProduct: (shopItemInfo: ShopItemInfo) -> Unit = {}
) {
    var inputProductNameDialogIsShowing by rememberSaveable { mutableStateOf(false) }
    var productCategoryChooserDialogIsShowing by rememberSaveable { mutableStateOf(false) }
    var nameOfProduct by rememberSaveable { mutableStateOf("") }

    Box(modifier) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.background),
            contentDescription = "Background",
            contentScale = ContentScale.Crop
        )

        if (inputProductNameDialogIsShowing) {
            InputProductNameDialog(
                products = shopItemInfos.map { it.name },
                onDismissRequest = { inputProductNameDialogIsShowing = false },
                onConfirmClick = { productName ->
                    nameOfProduct = productName
                    productCategoryChooserDialogIsShowing = true
                    inputProductNameDialogIsShowing = false
                },
                onDismissClick = { inputProductNameDialogIsShowing = false })
        }

        if (productCategoryChooserDialogIsShowing) {
            ProductCategoryChooserDialog(
                categories = shopItemInfos.map { it.category },
                onDismissRequest = { productCategoryChooserDialogIsShowing = false },
                onConfirmClick = { nameOfCategory ->
                    onAddNewProduct.invoke(
                        ShopItemInfo(
                            id = shopItemInfos.lastIndex,
                            name = nameOfProduct,
                            category = nameOfCategory
                        )
                    )
                    productCategoryChooserDialogIsShowing = false
                },
                onDismissClick = { productCategoryChooserDialogIsShowing = false })
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(vertical = 5.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(shopItemInfos) { shopItemInfo ->
                    ShopItem(shopItemInfo = shopItemInfo)
                }
            }
            ShopAddButton(
                modifier = Modifier.align(Alignment.End),
                onAddShopItem = { inputProductNameDialogIsShowing = true })
        }
    }
}

@Preview
@Composable
private fun ShopScreenPreview() {
    ShopScreen()
}