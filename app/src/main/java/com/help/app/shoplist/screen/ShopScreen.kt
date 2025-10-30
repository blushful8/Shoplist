package com.help.app.shoplist.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.help.app.shoplist.R
import com.help.app.shoplist.model.ShopItemInfo
import com.help.app.shoplist.shop.ShopAddButton
import com.help.app.shoplist.shop.ShopItem
import com.help.app.shoplist.ui.theme.AddShopButtonColor

@Composable
fun ShopScreen(
    modifier: Modifier = Modifier, shopItemInfos: List<ShopItemInfo> = listOf(
        ShopItemInfo()
    )
) {
    Box(modifier) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.background),
            contentDescription = "Background",
            contentScale = ContentScale.Crop
        )
        Column(modifier = modifier
            .fillMaxSize()
            .padding(10.dp)) {
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
            ShopAddButton(modifier = Modifier.align(Alignment.End), onAddShopItem = {})
        }
    }
}

@Preview
@Composable
private fun ShopScreenPreview() {
    ShopScreen()
}