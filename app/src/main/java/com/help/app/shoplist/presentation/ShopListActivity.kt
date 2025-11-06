package com.help.app.shoplist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.help.app.shoplist.presentation.vm.ShopListViewModel
import com.help.app.shoplist.presentation.screen.ShopScreen
import com.help.app.shoplist.core.ui.theme.ShoplistTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopListActivity : ComponentActivity() {
    private val shopListViewModel: ShopListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoplistTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShopScreen(
                        modifier = Modifier.padding(innerPadding),
                        shopItemInfos = shopListViewModel.itemsNeedToShop.collectAsState().value,
                        historyItems = shopListViewModel.historyItems.collectAsState().value,
                        onAddNewProduct = { shopItemInfo ->
                            shopListViewModel.addItem(shopItemInfo)
                        },
                        onDeleteBoughtProducts = { boughtShopItems ->
                            boughtShopItems.forEach { shopItemInfo ->
                                shopListViewModel.deleteItem(shopItemInfo)
                            }
                        },
                        onUpdateProductInfo = { shopItemInfo ->
                            shopListViewModel.updateItem(shopItemInfo)
                        }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    ShoplistTheme {
        ShopScreen()
    }
}