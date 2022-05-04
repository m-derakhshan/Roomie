package m.derakhshan.roomie.feature_home.presentation

import m.derakhshan.roomie.feature_home.presentation.filter_section.FilterState

sealed class HomeEvent {
    data class ChangeInternetState(val isConnected: Boolean) : HomeEvent()
    data class PlaceListSwiped(val offset: Float) : HomeEvent()
    data class UpdateAppliedFilter(val appliedFilter: FilterState) : HomeEvent()
    object ToggleFilterSearchListVisibility : HomeEvent()
}
