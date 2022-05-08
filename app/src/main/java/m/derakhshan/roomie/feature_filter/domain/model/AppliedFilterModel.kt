package m.derakhshan.roomie.feature_filter.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppliedFilterModel(
    @PrimaryKey
    val id: Int = 1,
    val search: String = "",
    val selectedPropertyTypeId: String = "0",
    val priceRange: ClosedFloatingPointRange<Float> = 0f..1f,
    val availableFrom: String = DateModel.today.toString(),
    val equipments: List<String> = emptyList(),
    val propertyFeatures: List<String> = emptyList()
)
