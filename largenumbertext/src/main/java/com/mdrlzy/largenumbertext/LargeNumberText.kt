package com.mdrlzy.largenumbertext

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun LargeNumberText(
    modifier: Modifier = Modifier,
    number: BigDecimal,
    buildText: (formattedNumber: String) -> String,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    softWrap: Boolean = true,
    maxLines: Int = 1,
    minLines: Int = 1,
    style: TextStyle = LocalTextStyle.current,
    overflow: TextOverflow = TextOverflow.Clip,
) {
    var textReadyToDraw by remember(key1 = number) {
        mutableStateOf(false)
    }
    var currentScale by remember(key1 = number) {
        mutableStateOf(Scale.ONE)
    }
    Text(
        modifier =
        modifier.drawWithContent {
            if (textReadyToDraw) {
                drawContent()
            }
        },
        text = buildText(shortNumber(number, currentScale)),
        color = color,
        textAlign = textAlign,
        fontSize = fontSize,
        fontFamily = fontFamily,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        lineHeight = lineHeight,
        style = style,
        maxLines = maxLines,
        minLines = minLines,
        softWrap = softWrap,
        overflow = if (textReadyToDraw) overflow else TextOverflow.Clip,
        onTextLayout = { result ->
            if (result.hasVisualOverflow) {
                if (currentScale == Scale.QUETTA) {
                    textReadyToDraw = true
                } else {
                    currentScale = currentScale.next()
                }
                return@Text
            }
            textReadyToDraw = true
        },
    )
}

private fun shortNumber(number: BigDecimal, scale: Scale): String {
    val shortNumber = number.divide(scale.value, 0, RoundingMode.HALF_EVEN).toPlainString()
    return "$shortNumber${scale.symbol}"
}