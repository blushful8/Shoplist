package com.help.app.shoplist.ui.widget

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.help.app.shoplist.ui.CustomText

@Composable
fun GridRadioGroup(
    defaultCategory: String = "A",
    categories: List<String> = listOf("A", "B", "C", "D", "E", "F", "G", "H", "I"),
    onChangeCategory: (categoryName: String) -> Unit = {}
) {
    val (selectedOption, onOptionSelected) = remember {
        val defaultIndex = categories.indexOf(defaultCategory)
        mutableStateOf(categories[defaultIndex])
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            Row(
                Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }) {
                        onOptionSelected(category)
                        onChangeCategory.invoke(category)
                    }
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(
                        2.dp,
                        if (category == selectedOption) MaterialTheme.colorScheme.primary else Color.Gray,
                        RoundedCornerShape(8.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    modifier = Modifier.padding(10.dp),
                    selected = (category == selectedOption),
                    onClick = null
                )
                Spacer(modifier = Modifier.width(4.dp))
                CustomText(text = category)
            }
        }
    }
}

@Preview
@Composable
private fun GridRadioGroupPreview() {
    GridRadioGroup()
}