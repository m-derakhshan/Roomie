package m.derakhshan.roomie.feature_filter.presentation

import m.derakhshan.roomie.feature_filter.domain.model.DateModel
import m.derakhshan.roomie.feature_property.domain.model.EquipmentModel
import m.derakhshan.roomie.feature_property.domain.model.PropertyFeatureModel
import m.derakhshan.roomie.feature_property.domain.model.PropertyTypeModel

sealed class FilterEvent {
    data class SearchValueChange(val search: String) : FilterEvent()
    data class UpdatePriceRange(val range: ClosedFloatingPointRange<Float>) : FilterEvent()
    data class UpdateSelectedPropertyType(val type: PropertyTypeModel) : FilterEvent()
    data class UpdateSelectedEquipment(val equipment: EquipmentModel) : FilterEvent()
    data class UpdateAvailableFrom(val date: DateModel) : FilterEvent()
    data class UpdatePropertyFeature(val feature: PropertyFeatureModel, val add: Boolean) :
        FilterEvent()

    object ConfirmAppliedFilter : FilterEvent()
    object ResetAppliedFilter : FilterEvent()
    object ScrollingUp : FilterEvent()
    object ScrollingDown : FilterEvent()
}
