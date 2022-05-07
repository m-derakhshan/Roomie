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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import m.derakhshan.roomie.feature_property.domain.model.PropertyModel
import m.derakhshan.roomie.ui.theme.space


@Composable
fun SpecialPlaceItem(item: PropertyModel, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart) {
        AsyncImage(
            model = item.images.first(),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = item.address,
        )

        Column(modifier = Modifier.background(MaterialTheme.colors.onBackground.copy(alpha = 0.6f))) {
            Text(
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.space.small,
                        bottom = MaterialTheme.space.extraSmall
                    ),
                text = "${item.rent} + ${item.expenses} expenses",
                color = MaterialTheme.colors.background
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = MaterialTheme.space.small,
                        bottom = MaterialTheme.space.small
                    ),
                text = item.address,
                color = MaterialTheme.colors.background,
                overflow = TextOverflow.Ellipsis
            )
        }

    }

}