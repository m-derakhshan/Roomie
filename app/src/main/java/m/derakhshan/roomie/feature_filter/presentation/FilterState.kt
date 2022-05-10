package m.derakhshan.roomie.feature_filter.presentation

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import m.derakhshan.roomie.feature_filter.domain.model.DateModel
import m.derakhshan.roomie.feature_property.domain.model.EquipmentModel
import m.derakhshan.roomie.feature_property.domain.model.PropertyFeatureModel
import m.derakhshan.roomie.feature_property.domain.model.PropertyTypeModel


data class FilterState(
    val searchValue: String = "",
    val selectedPropertyTypeId: String = "0",
    val priceRange: ClosedFloatingPointRange<Float> = 300f..2000f,
    val priceRangeLimit: ClosedFloatingPointRange<Float> = 300f..2000f,
    val availableFrom: DateModel = DateModel.today,
    val propertyType: List<PropertyTypeModel> = emptyList(),
    val equipments: List<EquipmentModel> = emptyList(),
    val propertyFeatures: List<PropertyFeatureModel> = emptyList(),
    val fabOffset: Dp = 0.dp
)
