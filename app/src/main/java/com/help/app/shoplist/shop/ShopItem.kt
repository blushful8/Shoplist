package com.help.app.shoplist.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.help.app.shoplist.data.model.ShopItemInfo
import com.help.app.shoplist.ui.theme.BottomCardColor
import com.help.app.shoplist.ui.theme.TopCardColor

@Composable
fun ShopItem(shopItemInfo: ShopItemInfo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { },
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
                ),
            contentAlignment = Alignment.CenterStart
        ) {

            Text(
                text = shopItemInfo.name,
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}