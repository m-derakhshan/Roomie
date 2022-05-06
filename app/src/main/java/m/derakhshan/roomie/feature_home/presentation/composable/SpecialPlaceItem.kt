package m.derakhshan.roomie.feature_home.presentation.composable


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import m.derakhshan.roomie.feature_property.domain.model.PropertyModel
import m.derakhshan.roomie.ui.theme.space


@Composable
fun SpecialPlaceItem(item: PropertyModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = item.images.first(),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = item.address,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .alpha(0.6f)
                .background(MaterialTheme.colors.onBackground)
                .align(Alignment.BottomCenter)
        )

        Text(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.space.small,
                    bottom = MaterialTheme.space.small
                )
                .align(Alignment.BottomStart),
            text = item.price,
            color = MaterialTheme.colors.background
        )

        Text(
            modifier = Modifier
                .padding(
                    end = MaterialTheme.space.small,
                    bottom = MaterialTheme.space.small
                )
                .align(Alignment.BottomEnd),
            text = item.address,
            color = MaterialTheme.colors.background
        )
    }

}