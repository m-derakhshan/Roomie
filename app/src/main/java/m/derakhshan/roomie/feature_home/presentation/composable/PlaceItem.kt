package m.derakhshan.roomie.feature_home.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import m.derakhshan.roomie.feature_property.domain.model.PropertyModel
import m.derakhshan.roomie.ui.theme.*

@Composable
fun PropertyItem(modifier: Modifier = Modifier, item: PropertyModel, contentDescription: String? = null) {

    Column(
        modifier = modifier
            .padding(
                start = MaterialTheme.space.medium,
                end = MaterialTheme.space.medium,
                bottom = MaterialTheme.space.small,
                top = MaterialTheme.space.large
            )
            .fillMaxWidth()
            .height(220.dp)
            .shadow(1.dp, RoundedCornerShape(10.dp))
            .clip(shape = RoundedCornerShape(10.dp))
            .background(VeryLightGray, shape = RoundedCornerShape(10.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            AsyncImage(
                model = item.images.first(),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = contentDescription,
            )
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.BottomStart)
                    .background(White, shape = RoundedCornerShape(5.dp))
                    .border(2.dp, color = VeryLightBlue, shape = RoundedCornerShape(5.dp))
                    .padding(horizontal = 15.dp, vertical = 3.dp)
            ) {
                Text(
                    color = Blue,
                    text = "${item.rent} + ${item.expenses} expenses",
                    style = MaterialTheme.typography.body1
                )
            }
        }

        Text(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.space.small,
                    bottom = MaterialTheme.space.small
                ),
            style = MaterialTheme.typography.body1,
            text = item.description,
            color = MaterialTheme.colors.onBackground
        )

        Text(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.space.small,
                    bottom = MaterialTheme.space.small
                ),
            style = MaterialTheme.typography.body2,
            text = item.address,
            color = MaterialTheme.colors.onBackground
        )
    }

}