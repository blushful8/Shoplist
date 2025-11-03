package com.help.app.shoplist.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.help.app.shoplist.ui.CustomButton
import com.help.app.shoplist.ui.CustomText
import com.help.app.shoplist.ui.widget.GridRadioGroup

@Composable
fun ProductCategoryChooserDialog(
    categories: List<String> = listOf("A", "B", "C"),
    onDismissRequest: () -> Unit = {},
    onConfirmClick: (nameOfCategory: String) -> Unit = {},
    onDismissClick: () -> Unit = {}
) {
    var categoryName by rememberSaveable { mutableStateOf("") }

    Dialog(onDismissRequest = onDismissRequest) {
        Column {
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
                placeholder = { CustomText(text = "Vegetables") },
                singleLine = true
            )
            HorizontalDivider(thickness = 1.dp, modifier = Modifier.height(10.dp))
            GridRadioGroup(categories.random(), categories, onChangeCategory = { newCategory ->
                categoryName = newCategory
            })
            HorizontalDivider(thickness = 1.dp, modifier = Modifier.height(10.dp))
            Row {
                CustomButton(text = "Apply", onClick = { onConfirmClick.invoke(categoryName) })
                Spacer(Modifier.width(10.dp))
                CustomButton(text = "Cancel", onClick = onDismissClick)
            }
        }
    }
}

@Preview
@Composable
private fun ProductCategoryChooserDialogPreview() {
    ProductCategoryChooserDialog()
}