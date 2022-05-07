package m.derakhshan.roomie.feature_property.presentation

sealed class PropertyEvent {
    data class SliderSwiped(val page: Int) : PropertyEvent()
    object ToggleDescriptionExpansion : PropertyEvent()
}