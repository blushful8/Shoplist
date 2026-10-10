package com.help.app.shoplist.presentation.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.help.app.shoplist.R
import com.help.app.shoplist.domain.model.HistoryItemInfo
import com.help.app.shoplist.domain.model.NewShopItemDetails
import com.help.app.shoplist.presentation.dialog.InputPickerDialog
import com.help.app.shoplist.domain.model.ShopItemInfo
import com.help.app.shoplist.presentation.shop.CategoryCroup
import com.help.app.shoplist.presentation.shop.ShopAddButton
import com.help.app.shoplist.presentation.shop.ShopDeleteButton

@Composable
fun ShopScreen(
    modifier: Modifier = Modifier,
    shopItemInfos: List<ShopItemInfo> = emptyList(),
    historyItems: List<HistoryItemInfo> = emptyList(),
    onAddNewProduct: () -> Unit = {},
    onDeleteBoughtProducts: (shopItemInfos: List<ShopItemInfo>) -> Unit = {},
    onUpdateProductInfo: (shopItemInfo: ShopItemInfo) -> Unit = {},
    onInputProductDetails: (NewShopItemDetails) -> Unit = {},
    onInputCategoryDetails: (NewShopItemDetails) -> Unit = {}
) {
    var inputProductNameDialogIsShowing by rememberSaveable { mutableStateOf(false) }
    var productCategoryChooserDialogIsShowing by rememberSaveable { mutableStateOf(false) }

    val groupedItems = shopItemInfos.groupBy { it.categoryName }
    Box(modifier) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.background),
            contentDescription = "Background",
            contentScale = ContentScale.Crop
        )

        if (inputProductNameDialogIsShowing) {
            InputPickerDialog(
                title = "Enter product name",
                hint = "Product name",
                withAdditionInputField = true,
                savedInputNames = historyItems.map { it.productName },
                onDismissRequest = { inputProductNameDialogIsShowing = false },
                onConfirmClick = { details ->
                    onInputProductDetails.invoke(details)
                    productCategoryChooserDialogIsShowing = true
                    inputProductNameDialogIsShowing = false
                },
                onDismissClick = { inputProductNameDialogIsShowing = false })
        }

        if (productCategoryChooserDialogIsShowing) {
            InputPickerDialog(
                title = "Input category",
                hint = "Vegetable",
                savedInputNames = historyItems.map { it.categoryName },
                onDismissRequest = { productCategoryChooserDialogIsShowing = false },
                onConfirmClick = { details ->
                    onInputCategoryDetails.invoke(details)
                    onAddNewProduct.invoke()
                    productCategoryChooserDialogIsShowing = false
                },
                onDismissClick = { productCategoryChooserDialogIsShowing = false })
        }


        var bottomBoxCoordinates by remember {
            mutableStateOf<LayoutCoordinates?>(null)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .onGloballyPositioned { layoutCoordinates ->
                    bottomBoxCoordinates = layoutCoordinates
                }
        ) {
            var lazyColumnRect by remember {
                mutableStateOf(
                    Rect(
                        Offset.Zero,
                        Size(0f, 0f)
                    )
                )
            }
            var bottomRowRect by remember {
                mutableStateOf(
                    Rect(
                        Offset.Zero,
                        Size(0f, 0f)
                    )
                )
            }


            val listState = rememberLazyListState()
            var lastVisibleIndex by remember {
                mutableIntStateOf(-1)
            }

            LaunchedEffect(listState) {
                snapshotFlow {
                    listState.layoutInfo.visibleItemsInfo.lastOrNull()?.let { lastVisibleItem ->
                        val top = lazyColumnRect.top + lastVisibleItem.offset

                        Rect(
                            left = lazyColumnRect.left,
                            top = top,
                            right = lazyColumnRect.right,
                            bottom = top + lastVisibleItem.size
                        ) to lastVisibleItem.index
                    }
                }.collect { result ->
                    result?.let { (lastVisibleItemRect, index) ->

                        if (lastVisibleItemRect.overlaps(bottomRowRect)) {
                            lastVisibleIndex = index
                        } else if (lastVisibleIndex != -1)
                            lastVisibleIndex = -1
                    }
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 5.dp)
                    .onGloballyPositioned {
                        lazyColumnRect = it.boundsInParent()
                    },
                state = listState,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(
                    items = groupedItems.toList(),
                    key = { _, (categoryName, _) -> categoryName }
                ) { index, (_, shopItemsList) ->
                    val paddingValue by animateDpAsState(
                        if (index == lastVisibleIndex)
                            150.dp
                        else 0.dp
                    )

                    CategoryCroup(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                end = paddingValue
                            )
                            .animateItem(
                                fadeInSpec = spring(stiffness = Spring.StiffnessVeryLow),
                                fadeOutSpec = spring(stiffness = Spring.StiffnessVeryLow)
                            )
                            .onGloballyPositioned { layoutCoordinates ->
                                bottomBoxCoordinates?.let { layoutCoordinates.localPositionOf(it) }
                            },
                        shopItemList = shopItemsList,
                        onBoughtChange = { isBought, shopItemInfo ->
                            onUpdateProductInfo.invoke(shopItemInfo.copy(productIsBought = isBought))
                        }
                    )
                }

            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .onGloballyPositioned { layoutCoordinates ->
                        bottomRowRect = layoutCoordinates.boundsInParent()
                    }
                    .padding(end = 12.dp, bottom = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                ShopDeleteButton(onDeleteShopItem = {
                    val boughtItems = shopItemInfos.filter { it.productIsBought }
                    if (boughtItems.isNotEmpty())
                        onDeleteBoughtProducts.invoke(boughtItems)
                })
                ShopAddButton(
                    onAddShopItem = {
                        inputProductNameDialogIsShowing = true
                    }
                )
            }

        }
    }
}


private fun mockItemInfos(): List<ShopItemInfo> {
    val items = mutableListOf<ShopItemInfo>()

    val productNames = listOf(
        "Potato", "Carrot", "Tomato",
        "Cucumber", "Yogurt", "Milk"
    )

    val productCategories = listOf(
        "Dairy Products",
        "Meat",
        "Fish and Seafood",
        "Fruits and Berries"
    )

    repeat((12..50).random()) {
        items.add(
            ShopItemInfo(
                productName = productNames.random(),
                categoryName = productCategories.random()
            )
        )
    }

    return items
}

@Preview
@Composable
private fun FullEmptyShopScreenPreview() {
    ShopScreen(
        shopItemInfos = mockItemInfos()
    )
}


@Preview
@Composable
private fun EmptyShopScreenPreview() {
    ShopScreen()
}