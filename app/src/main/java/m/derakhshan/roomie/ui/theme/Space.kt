package m.derakhshan.roomie.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

data class Space(
    val default: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 64.dp
)

val LocalSpacing = compositionLocalOf {
    Space()
}

val MaterialTheme.space: Space
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current

fun PaddingValues.add(b: PaddingValues): PaddingValues {
    val direction = LayoutDirection.Ltr
    return PaddingValues(
        top = this.calculateTopPadding() + b.calculateTopPadding(),
        bottom = this.calculateBottomPadding() + b.calculateBottomPadding(),
        start = this.calculateStartPadding(layoutDirection = direction) + b.calculateStartPadding(
            direction
        ),
        end = this.calculateEndPadding(direction) + b.calculateEndPadding(direction)
    )
}
fun PaddingValues.add(b: Dp): PaddingValues {
    val direction = LayoutDirection.Ltr
    with(PaddingValues(b)){
        return PaddingValues(
            top = this.calculateTopPadding() + this.calculateTopPadding(),
            bottom = this.calculateBottomPadding() + this.calculateBottomPadding(),
            start = this.calculateStartPadding(layoutDirection = direction) + this.calculateStartPadding(
                direction
            ),
            end = this.calculateEndPadding(direction) + this.calculateEndPadding(direction)
        )
    }
}

