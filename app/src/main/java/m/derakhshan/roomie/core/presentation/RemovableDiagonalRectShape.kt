package m.derakhshan.roomie.core.presentation

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class RemovableDiagonalRectShape(private val offset: Float) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = drawPath(size = size, offset = offset)
        )
    }

    private fun drawPath(size: Size, offset: Float): Path {
        return Path().apply {
            reset()
            lineTo(x = size.width, y = 0f)
            lineTo(x = size.width, y = size.height)
            lineTo(x = offset * 2, y = size.height)
            lineTo(x = offset * 1.2f, y = 0f)
            close()
        }
    }

}