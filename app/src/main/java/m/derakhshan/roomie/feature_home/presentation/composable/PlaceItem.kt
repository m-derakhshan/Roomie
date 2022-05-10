package m.derakhshan.roomie.feature_home.presentation.composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.PinDrop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import m.derakhshan.roomie.R
import m.derakhshan.roomie.core.NavGraph
import m.derakhshan.roomie.feature_property.domain.model.PropertyModel
import m.derakhshan.roomie.ui.theme.*

@Composable
fun PropertyItem(
    modifier: Modifier = Modifier,
    item: PropertyModel,
    contentDescription: String? = null
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MaterialTheme.space.small)
                .padding(vertical = MaterialTheme.space.extraSmall)
        ) {
            Icon(
                imageVector = Icons.Outlined.DateRange,
                contentDescription = null,
                tint = DarkBlue
            )
            Text(
                modifier = Modifier.padding(start = MaterialTheme.space.extraSmall),
                text = "${stringResource(id = R.string.available_from)}: ${item.availableFrom}",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MaterialTheme.space.small)
                .padding(vertical = MaterialTheme.space.extraSmall)
        ) {
            Icon(
                imageVector = Icons.Outlined.PinDrop,
                contentDescription = null,
                tint = DarkBlue
            )
            Text(
                modifier = Modifier.padding(start = MaterialTheme.space.extraSmall),
                text = "${stringResource(id = R.string.address)}: ${item.address}",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
            )
        }

        Text(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.space.small,
                    start = MaterialTheme.space.small,
                    bottom = MaterialTheme.space.small
                ),
            style = MaterialTheme.typography.body1,
            text = item.description,
            color = MaterialTheme.colors.onBackground,
            overflow = TextOverflow.Ellipsis,
            maxLines = 3
        )


    }
}