package m.derakhshan.roomie.feature_property.presentation


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import m.derakhshan.roomie.feature_property.domain.model.PropertyModel
import m.derakhshan.roomie.feature_property.domain.repository.PropertyRepository
import javax.inject.Inject

@HiltViewModel
class PropertyViewModel @Inject constructor(
    private val repository: PropertyRepository,
    stateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(PropertyState())
    val state: State<PropertyState> = _state


    init {

        stateHandle.get<String>("propertyId")?.let { propertyId ->
            viewModelScope.launch {
                _state.value = _state.value.copy(
                    property = repository.getPropertyById(propertyId = propertyId),
                )
            }
            _state.value = _state.value.copy(
                sliderCounter = "1/${_state.value.property.images.size}"
            )
        }
    }


    fun onEvent(event: PropertyEvent) {
        when (event) {
            is PropertyEvent.SliderSwiped -> {
                _state.value = _state.value.copy(
                    sliderCounter = "${event.page + 1}/${_state.value.property.images.size}"
                )
            }
            PropertyEvent.ToggleDescriptionExpansion -> {
                _state.value = _state.value.copy(
                    expandDescription = !_state.value.expandDescription
                )
            }
        }
    }
}

