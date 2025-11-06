package com.help.app.shoplist.presentation.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.help.app.shoplist.core.ui.widget.CustomButton
import com.help.app.shoplist.core.ui.widget.CustomText
import com.help.app.shoplist.core.ui.widget.GridRadioGroup

@Composable
fun ProductCategoryChooserDialog(
    categories: List<String> = emptyList(),
    onDismissRequest: () -> Unit = {},
    onConfirmClick: (nameOfCategory: String) -> Unit = {},
    onDismissClick: () -> Unit = {}
) {
    var categoryName by rememberSaveable { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { CustomText(text = "Enter category name") },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = categoryName,
                    onValueChange = { newText ->
                        categoryName = newText
                    },
                    label = {
                        CustomText(
                            text = "Input category",
                            fontSize = 14.sp
                        )
                    },
                    placeholder = { CustomText(text = "Vegetable") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(10.dp))
                if (categories.isNotEmpty()) {
                    GridRadioGroup(
                        modifier = Modifier.fillMaxWidth(),
                        uniqueItems = categories.toSet(),
                        selectedItem = categoryName,
                        onSelectItem = { newCategory ->
                            categoryName = newCategory
                        })
                    HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(10.dp))
                }
            }
        }, confirmButton = {
            CustomButton(
                text = "Apply",
                onClick = { if (categoryName.isNotBlank()) onConfirmClick.invoke(categoryName) })
        }, dismissButton = {
            CustomButton(text = "Cancel", onClick = onDismissClick)
        })
}

@Preview
@Composable
private fun ProductCategoryChooserDialogPreview() {
    ProductCategoryChooserDialog(categories = listOf("A", "B", "C"))
}