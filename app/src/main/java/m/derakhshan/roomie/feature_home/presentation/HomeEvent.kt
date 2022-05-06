package m.derakhshan.roomie.feature_home.presentation



sealed class HomeEvent {
    data class ChangeInternetState(val isConnected: Boolean) : HomeEvent()
    object ToggleFilterSearchListVisibility : HomeEvent()
}
