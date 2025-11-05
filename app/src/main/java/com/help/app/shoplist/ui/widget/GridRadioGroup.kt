package com.help.app.shoplist.ui.widget

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.help.app.shoplist.ui.CustomText

@Composable
fun GridRadioGroup(
    modifier: Modifier = Modifier,
    uniqueItems: Set<String> = setOf("A", "B", "C", "D", "E", "F", "G", "H", "I"),
    selectedItem: String = "",
    onSelectItem: (itemName: String) -> Unit = {}
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(uniqueItems.toList()) { category ->
            var currentFontSize by remember(category) { mutableStateOf(16.sp) }

            Row(
                Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }) {
                        onSelectItem.invoke(category)
                    }
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(
                        2.dp,
                        if (category == selectedItem) MaterialTheme.colorScheme.primary else Color.Gray,
                        RoundedCornerShape(8.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    modifier = Modifier.padding(PaddingValues(top = 8.dp, bottom = 8.dp, start = 8.dp)),
                    selected = (category == selectedItem),
                    onClick = null
                )
                Spacer(modifier = Modifier.width(4.dp))
                CustomText(
                    modifier = Modifier.weight(1F).padding(PaddingValues(end = 4.dp)),
                    fontSize = currentFontSize,
                    text = category,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    onTextLayout = { textLayoutResult ->
                        Log.d("TAG", "overFlow ${textLayoutResult.didOverflowWidth} visual ${textLayoutResult.hasVisualOverflow}")
                        if (textLayoutResult.hasVisualOverflow) {
                            val newSize = currentFontSize * 0.95f
                            if (newSize > 8.sp && newSize != currentFontSize) {
                                Log.d("TAG", "size $currentFontSize")

                                currentFontSize = newSize
                            }
                        }
                    })
            }
        }
    }
}

@Preview
@Composable
private fun GridRadioGroupPreview() {
    GridRadioGroup()
}