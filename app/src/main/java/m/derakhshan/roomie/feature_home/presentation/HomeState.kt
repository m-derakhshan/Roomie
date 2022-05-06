package m.derakhshan.roomie.feature_home.presentation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import m.derakhshan.roomie.feature_property.domain.model.PropertyModel
import m.derakhshan.roomie.feature_filter.presentation.FilterState

data class HomeState(
    val filterOffset: Animatable<Float, AnimationVector1D> = Animatable(initialValue = 0f),
    val specialList: List<PropertyModel> = emptyList(),
    val isInternetConnected: Boolean = true,
    val isFilterListVisible: Boolean = false,
    val filters: FilterState = FilterState()
)
