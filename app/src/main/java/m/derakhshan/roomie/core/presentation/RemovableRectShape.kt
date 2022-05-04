package m.derakhshan.roomie.core.presentation


import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class RemovableRectShape(private val offset: Float) : Shape {

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
            lineTo(size.width, 0f)
            lineTo(size.width, offset)
            lineTo(0f, offset)
            close()
        }
    }

}