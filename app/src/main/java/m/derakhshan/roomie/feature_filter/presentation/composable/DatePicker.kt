package m.derakhshan.roomie.feature_filter.presentation.composable




import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import m.derakhshan.roomie.feature_filter.domain.model.DateModel
import m.derakhshan.roomie.feature_filter.domain.model.MyCalendar
import m.derakhshan.roomie.ui.theme.space

private val weekDayList = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
private val weekend = listOf("Sat")

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun DatePicker(
    titleBackground: Color = MaterialTheme.colors.primary,
    myCalendar: MyCalendar,
    preSelectedDates: List<DateModel> = emptyList(),
    singleMode: Boolean = true,
    disablePreviousDays: Boolean = true,
    onNexMonthClickListener: () -> Unit,
    onPreviousMonthClickListener: () -> Unit,
    selectedDateListener: (List<DateModel>) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(titleBackground),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onPreviousMonthClickListener) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "previous month",
                    tint = MaterialTheme.colors.onPrimary
                )
            }

            AnimatedContent(
                targetState = myCalendar.month,
                transitionSpec = {
                    if (targetState > initialState) {
                        slideInHorizontally(initialOffsetX = { it }) + fadeIn() with
                                slideOutHorizontally(targetOffsetX = { -it }) + fadeOut()
                    } else {
                        slideInHorizontally(initialOffsetX = { -it }) + fadeIn() with
                                slideOutHorizontally(targetOffsetX = { it }) + fadeOut()
                    }.using(
                        SizeTransform(clip = false)
                    )
                }
            ) { month ->
                Text(
                    text = "${myCalendar.year}  ${myCalendar.getMonthName(month)}",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(MaterialTheme.space.small),
                    color = MaterialTheme.colors.onPrimary
                )
            }


            IconButton(onClick = onNexMonthClickListener) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "next month",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }

        Spacer(modifier = Modifier.size(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
        ) {
            items(weekDayList) { day ->
                Text(
                    text = day,
                    textAlign = TextAlign.Center,
                    color = if (day in weekend) Color.Red else Color.Black
                )
            }
        }

        DaysList(
            days = myCalendar.generateDays(),
            preSelectedDates = preSelectedDates,
            singleMode = singleMode,
            selectedDateListener = selectedDateListener,
            disablePreviousDays = disablePreviousDays
        )

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DaysList(
    days: List<DateModel>,
    preSelectedDates: List<DateModel>,
    disablePreviousDays: Boolean,
    singleMode: Boolean,
    selectedDateListener: (List<DateModel>) -> Unit
) {
    val selectedList = remember { mutableStateListOf(DateModel.today) }
    if (singleMode)
        selectedList.clear()
    selectedList.addAll(preSelectedDates)

    Box(modifier = Modifier.fillMaxWidth()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            contentPadding = PaddingValues(top = 8.dp, start = 4.dp, end = 4.dp)
        ) {
            items(days) { day ->

                DayItem(
                    text = day.day.toString(),
                    textColor = Color.Black,
                    selectedDayColor = Color.Blue,
                    isSelected = day in selectedList,
                    isActive = if (disablePreviousDays) day > DateModel.today else true ,
                    isSpace = day.day == -1,
                    isToday = day == DateModel.today
                ) {

                    if (singleMode)
                        selectedList.clear()

                    if (day in selectedList)
                        selectedList.remove(day)
                    else
                        selectedList.add(day)

                    if (selectedList.isEmpty())
                        selectedList.add(DateModel.today)

                    selectedDateListener(selectedList)

                }

                Spacer(modifier = Modifier.size(40.dp))

            }
        }
    }

}