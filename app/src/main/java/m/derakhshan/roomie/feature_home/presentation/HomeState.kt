package m.derakhshan.roomie.feature_home.presentation

import m.derakhshan.roomie.feature_property.domain.model.PropertyModel

data class HomeState(
    val specialList: List<PropertyModel> = emptyList()
)
