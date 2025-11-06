package com.help.app.shoplist.presentation.shop

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.help.app.shoplist.domain.model.ShopItemInfo
import com.help.app.shoplist.core.ui.widget.RoundCheckbox

@Composable
fun ShopItem(
    shopItemInfo: ShopItemInfo,
    onBoughtChange: (Boolean) -> Unit = {}
) {
    Box(
        modifier = Modifier.padding(5.dp).fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = shopItemInfo.productName,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
        RoundCheckbox(
            modifier = Modifier.align(Alignment.CenterEnd),
            checked = shopItemInfo.productIsBought,
            onCheckedChange = onBoughtChange
        )
    }

}

@Preview
@Composable
private fun ShopItemPreview() {
    ShopItem(shopItemInfo = ShopItemInfo())
}