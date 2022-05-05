package m.derakhshan.roomie.feature_home.presentation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import m.derakhshan.roomie.feature_home.domain.model.PropertyModel
import m.derakhshan.roomie.feature_home.presentation.filter_section.FilterState

data class HomeState(
    val filterOffset: Animatable<Float, AnimationVector1D> = Animatable(initialValue = 0f),
    val specialList: List<PropertyModel> = emptyList(),
    val isInternetConnected: Boolean = true,
    val searchValue: String = "",
    val placesList: List<PropertyModel> = emptyList(),
    val placesListOffset: Float = 300f,
    val isFilterListVisible: Boolean = false,
    val appliesFilter: FilterState = FilterState()
)
