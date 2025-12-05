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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.help.app.shoplist.core.ui.theme.ShoplistTheme
import com.help.app.shoplist.core.ui.widget.CustomButton
import com.help.app.shoplist.core.ui.widget.CustomText
import com.help.app.shoplist.core.ui.widget.GridRadioGroup
import com.help.app.shoplist.domain.model.NewShopItemDetails

@Composable
fun InputPickerDialog(
    title: String = "",
    hint: String = "",
    savedInputNames: List<String> = emptyList(),
    withAdditionInputField: Boolean = false,
    onDismissRequest: () -> Unit = {},
    onConfirmClick: (details: NewShopItemDetails) -> Unit = {},
    onDismissClick: () -> Unit = {}
) {
    var inputName by remember { mutableStateOf("") }
    var inputItemDescription by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            CustomText(
                text = title
            )
        },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = inputName,
                    onValueChange = { inputName = it },
                    label = { CustomText(text = hint) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    isError = inputName.isEmpty()
                )
                if (withAdditionInputField) {
                    OutlinedTextField(
                        value = inputItemDescription,
                        onValueChange = { inputItemDescription = it },
                        label = { CustomText(text = "Additionally...") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(10.dp))
                if (savedInputNames.isNotEmpty()) {
                    GridRadioGroup(
                        modifier = Modifier.fillMaxWidth(),
                        uniqueItems = savedInputNames.toSet(),
                        selectedItem = inputName,
                        onSelectItem = { newName ->
                            inputName = newName
                        })
                    HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(10.dp))
                }
            }
        },
        confirmButton = {
            CustomButton(
                text = "Save",
                onClick = {
                    if (inputName.isNotBlank()) {
                        onConfirmClick.invoke(
                            NewShopItemDetails(
                                itemName = inputName,
                                itemDescription = inputItemDescription
                            )
                        )
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

@Preview
@Composable
private fun InputPickerDialogPreview() {
    ShoplistTheme {
        InputPickerDialog(
            title = "Title",
            hint = "Hint",
            savedInputNames = listOf("Example", "Example"),
            withAdditionInputField = true
        )
    }
}