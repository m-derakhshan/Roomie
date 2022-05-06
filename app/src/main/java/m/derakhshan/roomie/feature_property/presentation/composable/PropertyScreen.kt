package m.derakhshan.roomie.feature_property.presentation.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import m.derakhshan.roomie.feature_property.domain.model.PropertyModel
import m.derakhshan.roomie.feature_property.presentation.PropertyViewModel
import m.derakhshan.roomie.ui.theme.space
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun PropertyScreen(
    innerPadding: PaddingValues,
    propertyId: String?,
    navController: NavController,
    viewModel: PropertyViewModel = hiltViewModel()
) {
    val property = viewModel.state.value
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        CompositionLocalProvider(LocalOverScrollConfiguration provides null) {
            HorizontalPager(
                count = property.images.size,
                contentPadding =
                PaddingValues(
                    horizontal = MaterialTheme.space.large,
                    vertical = MaterialTheme.space.medium
                )
            ) { page ->
                Card(
                    Modifier
                        .graphicsLayer {
                            val pageOffset =
                                calculateCurrentOffsetForPage(page).absoluteValue
                            lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }
                        .height(200.dp),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    AsyncImage(model = property.images[page], contentDescription = "")
                }
            }
        }

    }

}