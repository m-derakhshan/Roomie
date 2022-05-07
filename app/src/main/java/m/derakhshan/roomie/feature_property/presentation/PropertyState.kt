package m.derakhshan.roomie.feature_property.presentation

import m.derakhshan.roomie.feature_property.domain.model.PropertyModel

data class PropertyState(
    val property: PropertyModel = PropertyModel(),
    val sliderCounter: String = "",
    val expandDescription: Boolean = false
)
