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
fun ShopDeleteButton(
    modifier: Modifier = Modifier,
    onDeleteShopItem: () -> Unit = {}
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onDeleteShopItem,
        containerColor = AddShopButtonColor
    ) {
        Icon(
            painter = painterResource(R.drawable.baseline_remove_shopping_cart_24),
            contentDescription = "Remove shop items",
            tint = Color.White
        )
    }
}

@Preview
@Composable
private fun ShopDeleteButtonPreview(){
    ShopDeleteButton()
}