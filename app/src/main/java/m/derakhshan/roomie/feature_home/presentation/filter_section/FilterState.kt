package m.derakhshan.roomie.feature_home.presentation.filter_section

import m.derakhshan.roomie.feature_home.domain.model.date.Date
import m.derakhshan.roomie.feature_home.domain.model.filter.EquipmentModel
import m.derakhshan.roomie.feature_home.domain.model.filter.PropertyFeatureModel
import m.derakhshan.roomie.feature_home.domain.model.filter.PropertyTypeModel


data class FilterState(
    val searchValue: String = "",
    val selectedPropertyType: PropertyTypeModel = PropertyTypeModel(),
    val priceRange: ClosedFloatingPointRange<Float> = 300f..2000f,
    val priceRangeLimit: ClosedFloatingPointRange<Float> = 300f..2000f,
    val availableFrom: Date = Date.today,
    val propertyType: List<PropertyTypeModel> = emptyList(),
    private val equipments: List<EquipmentModel> = emptyList(),
    private val propertyFeatures: List<PropertyFeatureModel> = emptyList()
)
