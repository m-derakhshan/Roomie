package m.derakhshan.roomie.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle

@Composable
fun TransparentHintTextField(
    text: String,
    hint: String,
    textColor: Color,
    hintColor: Color,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
    textStyle: TextStyle = TextStyle(color = textColor),
    singleLine: Boolean = false
) {

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChanged,
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth(),
            cursorBrush = SolidColor(textColor),
        )
        if (text.isEmpty())
            Text(text = hint, style = textStyle, color = hintColor)

    }

}