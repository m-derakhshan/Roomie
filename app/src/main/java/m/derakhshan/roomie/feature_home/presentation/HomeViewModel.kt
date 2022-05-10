package m.derakhshan.roomie.feature_home.presentation


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import m.derakhshan.roomie.feature_home.domain.repository.HomeRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(repository: HomeRepository) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLocalDatabase()
        }
        viewModelScope.launch {
            repository.getSpecialPlaces().collectLatest { list ->
                _state.value = _state.value.copy(
                    specialList = list
                )
            }
        }
    }
}