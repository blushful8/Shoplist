package com.help.app.shoplist.dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.help.app.shoplist.ui.CustomButton
import com.help.app.shoplist.ui.CustomText

@Composable
fun InputProductNameDialog(
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
            OutlinedTextField(
                value = productName,
                onValueChange = { productName = it },
                label = { CustomText(text = "Product name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
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