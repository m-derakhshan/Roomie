package m.derakhshan.roomie.feature_filter.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import m.derakhshan.roomie.feature_property.domain.model.PropertyFeatureModel

@Entity
data class AppliedFilterModel(
    @PrimaryKey
    val id: Int = 1,
    val search: String = "",
    val selectedPropertyTypeId: String = "0",
    val priceRange: ClosedFloatingPointRange<Float> = 0f..1f,
    val availableFrom: String = DateModel.today.toString(),
    val equipments: List<String> = emptyList(),
    val propertyFeatures: List<PropertyFeatureModel> = emptyList()
)
