package m.derakhshan.roomie.feature_home.presentation


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import m.derakhshan.roomie.core.model.Response
import m.derakhshan.roomie.feature_home.domain.repository.HomeRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(repository: HomeRepository) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        viewModelScope.launch {
            repository.getSpecialPlaces().collectLatest { list ->
                _state.value = _state.value.copy(
                    specialList = list
                )
            }
        }
        viewModelScope.launch {
            repository.getAllPlaces().collectLatest { list ->
                _state.value = _state.value.copy(
                    placesList = list
                )
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLocalDatabase().let {
                if (it is Response.Error)
                    _state.value = _state.value.copy(
                        isInternetConnected = true// TODO:  false
                    )
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ChangeInternetState -> {
                _state.value = _state.value.copy(
                    isInternetConnected = event.isConnected
                )
            }
            is HomeEvent.PlaceListSwiped -> {
                _state.value = _state.value.copy(
                    placesListOffset = event.offset
                )
            }
            HomeEvent.ToggleFilterSearchListVisibility -> {
                _state.value = _state.value.copy(
                    isFilterListVisible = !_state.value.isFilterListVisible
                )
            }
        }
    }

}