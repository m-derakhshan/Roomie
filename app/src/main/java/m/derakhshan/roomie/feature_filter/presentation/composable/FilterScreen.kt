package m.derakhshan.roomie.feature_filter.presentation.composable



import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import m.derakhshan.roomie.R
import m.derakhshan.roomie.core.presentation.TransparentHintTextField
import m.derakhshan.roomie.feature_filter.domain.model.DateModel
import m.derakhshan.roomie.feature_filter.domain.model.MyCalendar
import m.derakhshan.roomie.feature_filter.presentation.FilterEvent
import m.derakhshan.roomie.feature_filter.presentation.FilterViewModel
import m.derakhshan.roomie.feature_property.domain.model.EquipmentModel
import m.derakhshan.roomie.feature_property.domain.model.PropertyFeatureModel
import m.derakhshan.roomie.ui.theme.*
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterScreen(
    innerPadding: PaddingValues,
    viewModel: FilterViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value
    val equipments = rememberUpdatedState(newValue = state.equipments)
    val propertyFeature = rememberUpdatedState(newValue = state.propertyFeatures)
    val fabOffset by animateDpAsState(
        targetValue = state.fabOffset,
        animationSpec = tween(300, easing = LinearEasing)
    )
    val scrollState = rememberScrollState()
    Scaffold(
        modifier = Modifier.padding(innerPadding),
        topBar = {
            TopAppBar {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        style = MaterialTheme.typography.h6,
                        text = stringResource(id = R.string.filter),
                        modifier = Modifier.align(Alignment.Center)
                    )
                    IconButton(
                        onClick = { navController.navigateUp() },
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                    Text(
                        style = MaterialTheme.typography.body1,
                        text = stringResource(id = R.string.reset),
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(horizontal = MaterialTheme.space.extraSmall)
                            .clickable {
                                viewModel.onEvent(FilterEvent.ResetAppliedFilter)
                            }
                            .padding(MaterialTheme.space.extraSmall)
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(FilterEvent.ConfirmAppliedFilter)
            }, modifier = Modifier.offset(y = fabOffset)) {
                Icon(Icons.Default.ArrowForward, contentDescription = null)
            }
        }
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(MaterialTheme.space.medium)
        ) {

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
                    onValueChanged = { search ->
                        viewModel.onEvent(FilterEvent.SearchValueChange(search))
                    },
                    singleLine = true
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
                                if (item.id == state.selectedPropertyTypeId) VeryLightBlue else White,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .border(
                                2.dp,
                                if (item.id == state.selectedPropertyTypeId) Blue else LightBlue,
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
                            contentDescription = item.text,
                            modifier = Modifier.size(60.dp)
                        )
                        Text(
                            text = item.text,
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

            //--------------------(Property Features)--------------------//
            Spacer(modifier = Modifier.padding(MaterialTheme.space.small))
            Section(text = stringResource(id = R.string.property_features))
            Spacer(modifier = Modifier.padding(MaterialTheme.space.small))
            ApartmentFeature(featuresList = propertyFeature, listener = remember(viewModel) {
                { feature, add ->
                    viewModel.onEvent(FilterEvent.UpdatePropertyFeature(feature, add))
                }
            })


            //--------------------(Equipments)--------------------//
            Spacer(modifier = Modifier.padding(MaterialTheme.space.small))
            Section(text = stringResource(id = R.string.equipments))
            Spacer(modifier = Modifier.padding(MaterialTheme.space.small))
            Equipments(
                equipmentList = equipments,
                checkListener = remember(viewModel) {
                    {
                        viewModel.onEvent(FilterEvent.UpdateSelectedEquipment(it))
                    }
                }
            )


            //--------------------(available from)--------------------//
            Spacer(modifier = Modifier.padding(MaterialTheme.space.small))
            Section(text = stringResource(id = R.string.available_from))
            Spacer(modifier = Modifier.padding(MaterialTheme.space.small))
            Column(
                modifier = Modifier
                    .height(350.dp)
            ) {
                DateSection(
                    preselectedDate = state.availableFrom,
                    selectedDateListener = remember(viewModel) {
                        {
                            viewModel.onEvent(FilterEvent.UpdateAvailableFrom(it.first()))
                        }
                    }
                )
            }

        }
    }


}

@Composable
fun Section(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.body1,
    lineModifier: Modifier = Modifier
) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(text = text, modifier = modifier, style = textStyle)
        Box(
            modifier = lineModifier
                .height(2.dp)
                .weight(1f)
                .padding(start = MaterialTheme.space.extraSmall)
                .background(Blue)
        )
    }
}

@Composable
private fun DateSection(
    preselectedDate: DateModel,
    selectedDateListener: (List<DateModel>) -> Unit
) {
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
private fun Equipments(
    equipmentList: State<List<EquipmentModel>>,
    checkListener: (EquipmentModel) -> Unit
) {

    val equipments = equipmentList.value
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
                Text(text = equipments[item].text)

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
                    Text(text = equipments[item + 1].text)
                }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun ApartmentFeature(
    featuresList: State<List<PropertyFeatureModel>>,
    listener: (PropertyFeatureModel, Boolean) -> Unit
) {

    val features = featuresList.value
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
                    Text(text = "${feature.text}: ")
                    AnimatedContent(
                        targetState = feature.value,
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


@Composable
fun ScrollState.isScrollingUp(): Boolean {
    var previousScrollOffset by remember(this) { mutableStateOf(this.value) }
    return remember(this) {
        derivedStateOf {
            (previousScrollOffset - this.value >= 0).also {
                previousScrollOffset = this.value
            }
        }
    }.value
}