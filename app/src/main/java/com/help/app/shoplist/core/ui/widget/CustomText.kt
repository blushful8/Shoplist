package com.help.app.shoplist.core.ui.widget

import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    text: String = "",
    fontSize: TextUnit = 22.sp,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip
) {
    var style = MaterialTheme.typography.bodyLarge.copy(
        fontSize = fontSize
    )

    if (textAlign != null) {
        style = style.copy(textAlign = textAlign)
    }

    Text(
        modifier = modifier,
        text = text,
        style = style,
        maxLines = maxLines,
        overflow = overflow,
        autoSize = TextAutoSize.StepBased(
            minFontSize = 12.sp,
            maxFontSize = 16.sp,
            stepSize = 0.5.sp
        )
    )
}