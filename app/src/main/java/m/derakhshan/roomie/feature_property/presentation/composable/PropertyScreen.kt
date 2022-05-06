package m.derakhshan.roomie.feature_property.presentation.composable

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import m.derakhshan.roomie.R
import m.derakhshan.roomie.feature_property.presentation.PropertyViewModel
import m.derakhshan.roomie.ui.theme.*


@Composable
fun PropertyScreen(
    innerPadding: PaddingValues,
    viewModel: PropertyViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val sliderCounter = viewModel.sliderCounter.value

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        //--------------------(slider)--------------------//
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Slider(images = state.images, swiped = { viewModel.updateSliderCounter(it) })
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.mipmap.white_fade),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .height(2.dp)
                        .background(Blue)
                )
                Text(text = sliderCounter, modifier = Modifier.padding(MaterialTheme.space.small))
                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .height(2.dp)
                        .background(Blue)
                )

            }
        }


        //--------------------(description)--------------------//
        Column(modifier = Modifier.padding(horizontal = MaterialTheme.space.small)) {
            Text(text = state.address, style = MaterialTheme.typography.body1)

            Text(
                modifier = Modifier.padding(vertical = MaterialTheme.space.medium),
                color = DarkBlue,
                text = "${state.rent} + ${state.expenses} expenses",
                style = MaterialTheme.typography.h6
            )
            LazyRow {
                items(state.propertyFeatures) { feature ->
                    Box(
                        modifier = Modifier
                            .padding(MaterialTheme.space.small)
                            .background(
                                Blue,
                                shape = RoundedCornerShape(5.dp)
                            )
                    ) {
                        Text(
                            text = "${feature.text}:${feature.value}",
                            color = White,
                            modifier = Modifier.padding(
                                vertical = MaterialTheme.space.small,
                                horizontal = MaterialTheme.space.medium
                            )
                        )
                    }
                }
            }
            Text(
                text = state.description,
                modifier = Modifier.padding(vertical = MaterialTheme.space.extraSmall),
                style = MaterialTheme.typography.body1
            )
        }
    }

}

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
private fun Slider(images: List<String>, swiped: (Int) -> Unit) {
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            swiped(page)
        }
    }

    CompositionLocalProvider(LocalOverScrollConfiguration provides null) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            count = images.size,
        ) { page ->
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                model = images[page],
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
    }
}