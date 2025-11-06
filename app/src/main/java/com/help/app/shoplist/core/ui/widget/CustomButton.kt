package com.help.app.shoplist.core.ui.widget

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.help.app.shoplist.core.ui.theme.ShoplistTheme

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(12.dp)
    ) {
        CustomText(text = text)
    }
}

@Preview
@Composable
private fun CustomButtonPreview() {
    ShoplistTheme {
        CustomButton(text = "Preview")
    }
}