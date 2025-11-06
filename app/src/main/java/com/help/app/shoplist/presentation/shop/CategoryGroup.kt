package com.help.app.shoplist.presentation.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.help.app.shoplist.core.ui.theme.BottomCardColor
import com.help.app.shoplist.core.ui.theme.TopCardColor
import com.help.app.shoplist.domain.model.ShopItemInfo

@Composable
fun CategoryCroup(
    modifier: Modifier = Modifier,
    categoryName: String = "",
    shopItemList: List<ShopItemInfo> = emptyList(),
    onBoughtChange: (Boolean, ShopItemInfo) -> Unit = { _, _ -> }
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            TopCardColor, BottomCardColor
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CategoryHeader(categoryName = categoryName)

                shopItemList.forEach { shopItemInfo ->
                    ShopItem(
                        shopItemInfo = shopItemInfo,
                        onBoughtChange = { onBoughtChange.invoke(it, shopItemInfo) })
                }
            }
        }
    }
}


@Preview
@Composable
private fun CategoryGroupPreview() {
    CategoryCroup()
}