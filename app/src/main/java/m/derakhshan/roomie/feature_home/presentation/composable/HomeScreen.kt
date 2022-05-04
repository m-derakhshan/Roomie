package m.derakhshan.roomie.feature_home.presentation.composable



import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import m.derakhshan.roomie.R
import m.derakhshan.roomie.core.presentation.RemovableRectShape
import m.derakhshan.roomie.feature_home.presentation.HomeEvent
import m.derakhshan.roomie.feature_home.presentation.HomeViewModel
import m.derakhshan.roomie.feature_home.presentation.composable.filter_section.FilterSearchSection
import m.derakhshan.roomie.ui.theme.Blue
import m.derakhshan.roomie.ui.theme.LightBlue
import m.derakhshan.roomie.ui.theme.White
import m.derakhshan.roomie.ui.theme.space
import kotlin.math.absoluteValue


@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    innerPadding: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LaunchedEffect(state.isFilterListVisible) {
        state.filterOffset.animateTo(
            if (state.isFilterListVisible) 2500f else 0f,
            animationSpec = tween(1000)
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (state.filterOffset.value != 2000f)
                Column(
                    modifier = Modifier
                        .background(LightBlue)
                        .fillMaxWidth()
                ) {

                    //--------------------(search and filter)--------------------//
                    Row(
                        modifier = Modifier
                            .padding(MaterialTheme.space.medium)
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(White, shape = RoundedCornerShape(10.dp))
                            .clip(shape = RoundedCornerShape(10.dp))
                            .clickable {
                                viewModel.onEvent(HomeEvent.ToggleFilterSearchListVisibility)
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Row {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                modifier = Modifier.padding(start = MaterialTheme.space.small)
                            )
                            Text(
                                text = stringResource(id = R.string.search),
                                modifier = Modifier.padding(start = MaterialTheme.space.extraSmall)
                            )
                        }
                        Icon(
                            imageVector = Icons.Outlined.FilterAlt,
                            contentDescription = null,
                            modifier = Modifier.padding(end = MaterialTheme.space.small)
                        )

                    }

                    //--------------------(slogan)--------------------//
                    Column(
                        modifier = Modifier
                            .weight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.slogan_short),
                            modifier = Modifier
                                .fillMaxWidth(),
                            color = White,
                            style = MaterialTheme.typography.h4,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = stringResource(id = R.string.slogan_long),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = MaterialTheme.space.small,
                                    bottom = MaterialTheme.space.medium
                                ),
                            color = White,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center
                        )
                    }

                    //--------------------(special offer for you)--------------------//
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = White,
                                shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
                            )
                            .clip(shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = MaterialTheme.space.medium),
                            text = stringResource(id = R.string.special_offer_for_you),
                            style = MaterialTheme.typography.h6,
                            color = Blue
                        )
                        CompositionLocalProvider(LocalOverScrollConfiguration provides null) {
                            HorizontalPager(
                                count = 4,
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
                                    if (state.specialList.isNotEmpty())
                                        SpecialPlaceItem(state.specialList[page])
                                }
                            }
                        }
                    }
                }

            //--------------------(search and filter section)--------------------//
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RemovableRectShape(state.filterOffset.value))
                    .background(White)
            ) {
                FilterSearchSection{ filters, confirmed ->
                    if (confirmed)
                        viewModel.onEvent(HomeEvent.UpdateAppliedFilter(filters))
                    viewModel.onEvent(HomeEvent.ToggleFilterSearchListVisibility)
                }
            }
        }
    }
}



