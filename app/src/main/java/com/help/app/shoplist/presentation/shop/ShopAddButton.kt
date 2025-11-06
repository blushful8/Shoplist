package com.help.app.shoplist.presentation.shop

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.help.app.shoplist.R
import com.help.app.shoplist.core.ui.theme.AddShopButtonColor

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