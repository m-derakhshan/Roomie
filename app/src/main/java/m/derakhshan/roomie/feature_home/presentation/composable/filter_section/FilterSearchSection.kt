package m.derakhshan.roomie.feature_home.presentation.composable.filter_section


import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import m.derakhshan.roomie.R
import m.derakhshan.roomie.core.presentation.TransparentHintTextField
import m.derakhshan.roomie.feature_home.domain.model.date.Date
import m.derakhshan.roomie.feature_home.domain.model.date.MyCalendar
import m.derakhshan.roomie.feature_home.domain.model.filter.EquipmentModel
import m.derakhshan.roomie.feature_home.domain.model.filter.PropertyFeatureModel
import m.derakhshan.roomie.feature_home.presentation.filter_section.FilterEvent
import m.derakhshan.roomie.feature_home.presentation.filter_section.FilterState
import m.derakhshan.roomie.feature_home.presentation.filter_section.FilterViewModel
import m.derakhshan.roomie.ui.theme.*
import kotlin.math.abs
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterSearchSection(
    viewModel: FilterViewModel = hiltViewModel(),
    onButtonClickListener: (FilterState, Boolean) -> Unit
) {

    val state = viewModel.state.value
    var offset by remember { mutableStateOf(0f) }
    val animatedOffset by animateFloatAsState(
        targetValue = offset, animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(MaterialTheme.space.medium)
        ) {

            var hintVisibility by remember { mutableStateOf(state.searchValue.isEmpty()) }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(LightBlue, shape = RoundedCornerShape(10.dp))
                    .clip(shape = RoundedCornerShape(10.dp))
                    .padding(end = MaterialTheme.space.extraSmall),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.padding(start = MaterialTheme.space.small),
                    tint = White
                )
                TransparentHintTextField(
                    text = state.searchValue,
                    hint = stringResource(id = R.string.search),
                    textColor = White,
                    hintColor = White,
                    isHintVisible = hintVisibility,
                    onValueChanged = { search ->
                        viewModel.onEvent(FilterEvent.SearchValueChange(search))
                    },
                    singleLine = true,
                    onFocusChangeListener = {
                        if (it.isFocused)
                            hintVisibility = false
                        else if (state.searchValue.isEmpty())
                            hintVisibility = true
                    }
                )
            }

            //--------------------(property type section)--------------------//
            Spacer(modifier = Modifier.padding(MaterialTheme.space.small))
            Section(text = stringResource(id = R.string.property_type))
            Spacer(modifier = Modifier.padding(MaterialTheme.space.small))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items(items = state.propertyType) { item ->
                    Column(
                        modifier = Modifier
                            .padding(MaterialTheme.space.extraSmall)
                            .size(100.dp)
                            .background(
                                if (item.id == state.selectedPropertyType.id) VeryLightBlue else White,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .border(
                                2.dp,
                                if (item.id == state.selectedPropertyType.id) Blue else LightBlue,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .clip(shape = RoundedCornerShape(10.dp))
                            .clickable {
                                viewModel.onEvent(FilterEvent.UpdateSelectedPropertyType(item))
                            }
                            .padding(MaterialTheme.space.extraSmall),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        AsyncImage(
                            model = item.icon,
                            contentDescription = item.title,
                            modifier = Modifier.size(60.dp)
                        )
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }

            }

            //--------------------(price range section)--------------------//
            Spacer(modifier = Modifier.padding(MaterialTheme.space.small))
            Section(text = stringResource(id = R.string.price_range))
            Spacer(modifier = Modifier.padding(MaterialTheme.space.small))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "${(state.priceRange.start).roundToInt()}€")
                Text(text = "${(state.priceRange.endInclusive).roundToInt()}€")
            }
            RangeSlider(
                values = state.priceRange,
                onValueChange = { range ->
                    viewModel.onEvent(FilterEvent.UpdatePriceRange(range))
                },
                valueRange = state.priceRangeLimit,
            )

            //--------------------(available from)--------------------//
            Spacer(modifier = Modifier.padding(MaterialTheme.space.small))
            Section(text = stringResource(id = R.string.available_from))
            Spacer(modifier = Modifier.padding(MaterialTheme.space.small))
            Column(modifier = Modifier.height(350.dp)) {
                DateSection(
                    preselectedDate = state.availableFrom,
                    selectedDateListener = remember(viewModel) {
                        {
                            viewModel.onEvent(FilterEvent.UpdateAvailableFrom(it.first()))
                        }
                    }
                )
            }

            //--------------------(Equipments)--------------------//
            Spacer(modifier = Modifier.padding(MaterialTheme.space.small))
            Section(text = stringResource(id = R.string.equipments))
            Spacer(modifier = Modifier.padding(MaterialTheme.space.small))
            Equipments(equipments = viewModel.equipments, checkListener = remember(viewModel) {
                {
                    viewModel.onEvent(FilterEvent.UpdateSelectedEquipment(it))
                }
            })

            //--------------------(Property Features)--------------------//
            Spacer(modifier = Modifier.padding(MaterialTheme.space.small))
            Section(text = stringResource(id = R.string.property_features))
            Spacer(modifier = Modifier.padding(MaterialTheme.space.small))
            ApartmentFeature(features = viewModel.propertyFeatures, listener = remember(viewModel) {
                { feature, add ->
                    viewModel.onEvent(FilterEvent.UpdatePropertyFeature(feature, add))
                }
            })


        }
        //--------------------(confirm or reject selected filter)--------------------//
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.space.small),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    modifier = Modifier
                        .size(40.dp)
                        .alpha(0.25f),
                    imageVector = Icons.Outlined.Close,
                    contentDescription = null,
                    tint = Red
                )


                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "")
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "")


                Icon(
                    modifier = Modifier
                        .size(40.dp)
                        .alpha(0.25f),
                    imageVector = Icons.Outlined.Done,
                    contentDescription = null,
                    tint = Green
                )


            }

            Box(
                modifier = Modifier
                    .offset(x = animatedOffset.dp)
                    .shadow(2.dp, CircleShape)
                    .size(50.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center)
                    .draggable(
                        orientation = Orientation.Horizontal,
                        state = rememberDraggableState {
                            offset += (it * 0.35f)
                        },
                        onDragStopped = {
                            if (abs(offset) > 110f) {
                                if (offset < (-110f))
                                    viewModel.onEvent(FilterEvent.ResetAllFilters)
                                onButtonClickListener(state, (offset) > 110f)
                            }
                            offset = 0f
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(offset * 0.01f)
                        .background(Green)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(1f - offset * 0.01f)
                        .background(Blue)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha((-offset) * 0.01f)
                        .background(Red)
                )
                Icon(
                    imageVector = Icons.Outlined.FilterAlt,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }

    }

}

@Composable
private fun Section(
    text: String,
    modifier: Modifier = Modifier,
    lineModifier: Modifier = Modifier
) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(text = text, modifier = modifier, style = MaterialTheme.typography.body1)
        Box(
            modifier = lineModifier
                .height(1.dp)
                .weight(1f)
                .padding(start = MaterialTheme.space.extraSmall)
                .background(Blue)
        )
    }
}

@Composable
private fun DateSection(preselectedDate: Date, selectedDateListener: (List<Date>) -> Unit) {
    Log.i("Log", "DateSection: recomposed")
    var monthNumber by remember { mutableStateOf(0) }
    DatePicker(
        preSelectedDates = listOf(preselectedDate),
        myCalendar = MyCalendar(monthNumber),
        onPreviousMonthClickListener = { if (monthNumber > 0) monthNumber-- },
        onNexMonthClickListener = { monthNumber++ },
        selectedDateListener = selectedDateListener
    )
}

@Composable
private fun Equipments(equipments: List<EquipmentModel>, checkListener: (EquipmentModel) -> Unit) {
    Log.i("Log", "Equipments: recomposed")
    for (item in equipments.indices step 2) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.weight(0.5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Checkbox(
                    checked = equipments[item].isChecked,
                    onCheckedChange = { checkListener(equipments[item].copy(isChecked = it)) })
                Text(text = equipments[item].title)

            }
            if (item != equipments.lastIndex)
                Row(
                    modifier = Modifier.weight(0.5f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Checkbox(
                        checked = equipments[item + 1].isChecked,
                        onCheckedChange = { checkListener(equipments[item + 1].copy(isChecked = it)) })
                    Text(text = equipments[item + 1].title)
                }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun ApartmentFeature(
    features: List<PropertyFeatureModel>,
    listener: (PropertyFeatureModel, Boolean) -> Unit
) {
    Log.i("Log", "ApartmentFeature: recomposed")
    Column(modifier = Modifier.fillMaxWidth()) {
        features.forEach { feature ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {


                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(Blue, shape = RoundedCornerShape(5.dp))
                        .clip(shape = RoundedCornerShape(5.dp))
                        .clickable {
                            listener(feature, false)
                        }, contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = null,
                        tint = White
                    )
                }

                Row {
                    Text(text = "${feature.title}: ")
                    AnimatedContent(
                        targetState = feature.number,
                        transitionSpec = {
                            if (targetState > initialState) {
                                slideInVertically { height -> height } + fadeIn() with
                                        slideOutVertically { height -> -height } + fadeOut()
                            } else {
                                slideInVertically { height -> -height } + fadeIn() with
                                        slideOutVertically { height -> height } + fadeOut()
                            }.using(SizeTransform(clip = false))
                        }
                    ) { targetCount ->
                        Text(text = if (targetCount > 0) "$targetCount" else stringResource(id = R.string.all))
                    }
                }

                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(Blue, shape = RoundedCornerShape(5.dp))
                        .clip(shape = RoundedCornerShape(5.dp))
                        .clickable {
                            listener(feature, true)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = White)
                }
            }
            Spacer(modifier = Modifier.size(MaterialTheme.space.medium))
        }
    }

}