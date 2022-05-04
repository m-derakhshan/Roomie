package m.derakhshan.roomie.feature_home.domain.model.date

import java.util.*

data class Date(
    val day: Int,
    val month: Int,
    val year: Int
) {
    operator fun compareTo(date: Date): Int {
        return if (this.year < date.year) -1
        else if (this.year == date.year && this.month < date.month) -1
        else if (this.year == date.year && this.month == date.month && this.day < date.day)
            -1
        else 1
    }

    companion object {
        val today: Date
            get() {
                val calendar = Calendar.getInstance()
                return Date(
                    day = calendar.get(Calendar.DAY_OF_MONTH),
                    month = calendar.get(Calendar.MONTH) + 1,
                    year = calendar.get(Calendar.YEAR)
                )
            }
    }
}