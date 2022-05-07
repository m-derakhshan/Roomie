package m.derakhshan.roomie.feature_property.presentation.composable


import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.PinDrop
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import m.derakhshan.roomie.R
import m.derakhshan.roomie.feature_filter.presentation.composable.Section
import m.derakhshan.roomie.feature_property.domain.model.EquipmentModel
import m.derakhshan.roomie.feature_property.presentation.PropertyEvent
import m.derakhshan.roomie.feature_property.presentation.PropertyViewModel
import m.derakhshan.roomie.ui.theme.*


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PropertyScreen(
    innerPadding: PaddingValues,
    navController: NavController,
    viewModel: PropertyViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

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
            Slider(
                images = state.property.images,
                swiped = { viewModel.onEvent(PropertyEvent.SliderSwiped(it)) })
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
                Text(
                    text = state.sliderCounter,
                    modifier = Modifier.padding(MaterialTheme.space.small)
                )
                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .height(2.dp)
                        .background(Blue)
                )

            }
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .background(
                        color = LightGray.copy(alpha = 0.5f),
                        RoundedCornerShape(topEnd = 5.dp, bottomEnd = 5.dp)
                    )
                    .clip(shape = RoundedCornerShape(topEnd = 5.dp, bottomEnd = 5.dp))
                    .clickable { navController.navigateUp() }
                    .padding(
                        vertical = MaterialTheme.space.extraSmall,
                        horizontal = MaterialTheme.space.small
                    )
            ) {
                Icon(imageVector = Icons.Default.ArrowBackIos, contentDescription = "back")
            }

        }


        //--------------------(description)--------------------//
        Column(modifier = Modifier.padding(MaterialTheme.space.small)) {

            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    imageVector = Icons.Outlined.PinDrop,
                    contentDescription = null,
                    tint = DarkBlue
                )
                Text(
                    modifier = Modifier.padding(start = MaterialTheme.space.extraSmall),
                    text = "${stringResource(id = R.string.address)}: ${state.property.address}",
                    style = MaterialTheme.typography.body1,
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.space.medium)
            ) {
                Icon(
                    imageVector = Icons.Outlined.DateRange,
                    contentDescription = null,
                    tint = DarkBlue
                )
                Text(
                    modifier = Modifier.padding(start = MaterialTheme.space.extraSmall),
                    text = "${stringResource(id = R.string.available_from)}: ${state.property.availableFrom}",
                    style = MaterialTheme.typography.body1,
                )

            }

            Row(
                modifier = Modifier
                    .padding(vertical = MaterialTheme.space.medium)
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(start = MaterialTheme.space.extraSmall),
                    color = Blue,
                    text = "${state.property.rent} + ${state.property.expenses} expenses",
                    style = MaterialTheme.typography.h6
                )
            }
            //--------------------(apartment features)--------------------//
            LazyRow {
                items(state.property.propertyFeatures) { feature ->
                    Box(
                        modifier = Modifier
                            .padding(MaterialTheme.space.small)
                            .background(
                                LightGray,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .border(width = 1.dp, color = Gray, shape = RoundedCornerShape(5.dp))
                    ) {
                        Text(
                            text = "${feature.text}: ${feature.value}",
                            color = Black,
                            modifier = Modifier.padding(
                                vertical = MaterialTheme.space.small,
                                horizontal = MaterialTheme.space.medium
                            )
                        )
                    }
                }
            }
            //--------------------(description)--------------------//
            AnimatedContent(
                modifier = Modifier.clickable { viewModel.onEvent(PropertyEvent.ToggleDescriptionExpansion) },
                targetState = state.expandDescription,
                transitionSpec = {
                    fadeIn(animationSpec = tween(150)) with
                            fadeOut(animationSpec = tween(150)) using
                            SizeTransform { initialSize, targetSize ->
                                if (targetState) {
                                    keyframes {
                                        IntSize(initialSize.width, initialSize.height) at 150
                                        durationMillis = 500
                                    }
                                } else {
                                    keyframes {
                                        IntSize(initialSize.width, targetSize.height) at 200
                                        durationMillis = 600
                                    }
                                }
                            }
                }
            ) { targetExpanded ->

                Text(
                    text = when {
                        targetExpanded -> state.property.description
                        state.property.description.length > 200 -> state.property.description.substring(0, 200) + "..."
                        else -> state.property.description
                    },
                    modifier = Modifier.padding(vertical = MaterialTheme.space.extraSmall),
                    style = MaterialTheme.typography.body1
                )

            }


            //--------------------(equipments)--------------------//
            Spacer(modifier = Modifier.padding(MaterialTheme.space.small))
            Section(
                text = stringResource(id = R.string.equipments),
                textStyle = MaterialTheme.typography.body1.copy(fontSize = 18.sp)
            )
            Spacer(modifier = Modifier.padding(MaterialTheme.space.small))
            Equipments(equipments = state.property.equipments)
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

@Composable
fun Equipments(equipments: List<EquipmentModel>) {

    for (item in equipments.indices step 2) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.space.small)
        ) {
            Row(
                modifier = Modifier.weight(0.5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                AsyncImage(
                    modifier = Modifier.size(25.dp),
                    model = equipments[item].icon,
                    imageLoader = ImageLoader.Builder(LocalContext.current)
                        .components { add(SvgDecoder.Factory()) }
                        .build(),
                    contentDescription = equipments[item].title,
                )
                Text(
                    text = equipments[item].title,
                    modifier = Modifier.padding(start = MaterialTheme.space.small)
                )

            }
            if (item != equipments.lastIndex)
                Row(
                    modifier = Modifier.weight(0.5f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    AsyncImage(
                        model = equipments[item + 1].icon,
                        imageLoader = ImageLoader.Builder(LocalContext.current)
                            .components { add(SvgDecoder.Factory()) }
                            .build(),
                        contentDescription = equipments[item].title
                    )
                    Text(
                        text = equipments[item + 1].title,
                        modifier = Modifier.padding(start = MaterialTheme.space.small)
                    )
                }
        }
    }

}