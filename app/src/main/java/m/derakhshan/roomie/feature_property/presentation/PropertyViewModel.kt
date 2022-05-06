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

    private val _state = mutableStateOf(PropertyModel())
    val state: State<PropertyModel> = _state

    init {
        val propertyId = stateHandle.get<String>("propertyId") ?: "-1"
        viewModelScope.launch {
            _state.value = repository.getPropertyById(propertyId = propertyId) ?: PropertyModel()
        }
    }

}