package com.help.app.shoplist.presentation.shop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.help.app.shoplist.core.ui.widget.CustomText

@Composable
fun CategoryHeader(categoryName: String) {
    Column {
        CustomText(
            text = categoryName,
            fontSize = 26.sp,
            modifier = Modifier.padding(bottom = 4.dp, start = 10.dp)
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
        )
    }
}