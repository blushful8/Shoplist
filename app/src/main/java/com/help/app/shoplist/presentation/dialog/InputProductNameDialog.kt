package com.help.app.shoplist.presentation.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.help.app.shoplist.core.ui.widget.CustomButton
import com.help.app.shoplist.core.ui.widget.CustomText
import com.help.app.shoplist.core.ui.widget.GridRadioGroup

@Composable
fun InputProductNameDialog(
    historyProductNames: List<String> = emptyList(),
    onDismissRequest: () -> Unit = {},
    onConfirmClick: (nameOfProduct: String) -> Unit = {},
    onDismissClick: () -> Unit = {}
) {
    var productName by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            CustomText(
                text = "Enter product name"
            )
        },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = productName,
                    onValueChange = { productName = it },
                    label = { CustomText(text = "Product name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(10.dp))
                if (historyProductNames.isNotEmpty()) {
                    GridRadioGroup(
                        modifier = Modifier.fillMaxWidth(),
                        uniqueItems = historyProductNames.toSet(),
                        selectedItem = productName,
                        onSelectItem = { newProductName ->
                            productName = newProductName
                        })
                    HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(10.dp))
                }
            }
        },
        confirmButton = {
            CustomButton(
                text = "Save",
                onClick = {
                    if (productName.isNotBlank()) {
                        onConfirmClick.invoke(productName)
                    }
                }
            )
        },
        dismissButton = {
            CustomButton(
                text = "Cancel",
                onClick = onDismissClick
            )
        },
        shape = RoundedCornerShape(16.dp)
    )
}