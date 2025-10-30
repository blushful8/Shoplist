package com.help.app.shoplist.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.help.app.shoplist.R
import com.help.app.shoplist.ui.theme.AddShopButtonColor

@Composable
fun ShopAddButton(
    modifier: Modifier = Modifier,
    onAddShopItem: () -> Unit = {}
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onAddShopItem,
        containerColor = AddShopButtonColor
    ) {
        Icon(
            painter = painterResource(R.drawable.icons8_add_shopping_cart),
            contentDescription = "Add shop item",
            tint = Color.White
        )
    }
}


@Preview
@Composable
private fun ShopAddButtonPreview() {
    ShopAddButton()
}