package com.help.app.shoplist.presentation.shop

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.help.app.shoplist.core.ui.theme.ShoplistTheme
import com.help.app.shoplist.domain.model.ShopItemInfo
import com.help.app.shoplist.core.ui.widget.RoundCheckbox

@Composable
fun ShopItem(
    shopItemInfo: ShopItemInfo,
    onBoughtChange: (Boolean) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = shopItemInfo.productName,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    if (shopItemInfo.productNote.isNotBlank())
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            )
                        ) {
                            Text(
                                text = shopItemInfo.productNote,
                                color = MaterialTheme.colorScheme.onSecondary,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(
                                    start = 16.dp,
                                    top = 5.dp,
                                    end = 5.dp,
                                    bottom = 5.dp
                                )
                            )
                        }
                }


                RoundCheckbox(
                    checked = shopItemInfo.productIsBought,
                    onCheckedChange = onBoughtChange
                )
            }
        }

    }

}

@Preview
@Composable
private fun ShopItemPreview() {
    ShoplistTheme {
        ShopItem(shopItemInfo = ShopItemInfo())
    }
}