package m.derakhshan.roomie.feature_home.presentation.filter_section

import m.derakhshan.roomie.feature_home.domain.model.date.Date
import m.derakhshan.roomie.feature_home.domain.model.filter.EquipmentModel
import m.derakhshan.roomie.feature_home.domain.model.filter.PropertyFeatureModel
import m.derakhshan.roomie.feature_home.domain.model.filter.PropertyTypeModel

sealed class FilterEvent {
    data class SearchValueChange(val search: String) : FilterEvent()
    data class UpdatePriceRange(val range: ClosedFloatingPointRange<Float>) : FilterEvent()
    data class UpdateSelectedPropertyType(val type: PropertyTypeModel) : FilterEvent()
    data class UpdateSelectedEquipment(val equipment: EquipmentModel) : FilterEvent()
    data class UpdateAvailableFrom(val date: Date) : FilterEvent()
    data class UpdatePropertyFeature(val feature: PropertyFeatureModel, val add: Boolean) :
        FilterEvent()

    object AddAdditionalInfoToFilterList : FilterEvent()
    object ResetAllFilters : FilterEvent()
}
