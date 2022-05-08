package m.derakhshan.roomie.feature_home.presentation


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
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
class HomeViewModel @Inject constructor(repository: HomeRepository) : ViewModel(),
    DefaultLifecycleObserver {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLocalDatabase().let {
                if (it is Response.Error)
                    _state.value = _state.value.copy(
                        isInternetConnected = true// TODO:  false
                    )
            }
        }
        viewModelScope.launch {
            repository.getSpecialPlaces().collectLatest { list ->
                _state.value = _state.value.copy(
                    specialList = list
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
            HomeEvent.ToggleFilterSearchListVisibility -> {
                _state.value = _state.value.copy(
                    isFilterListVisible = !_state.value.isFilterListVisible
                )
            }
        }
    }

    override fun onCreate(owner: LifecycleOwner) {
        Log.i("Log", "onCreate: ")
        super.onCreate(owner)
    }

    override fun onStart(owner: LifecycleOwner) {
        Log.i("Log", "onStart: ")
        super.onStart(owner)
    }

    override fun onResume(owner: LifecycleOwner) {
        Log.i("Log", "onResume: ")
        super.onResume(owner)
    }

    override fun onPause(owner: LifecycleOwner) {
        Log.i("Log", "onPause: ")
        super.onPause(owner)
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.i("Log", "onStop: ")
        super.onStop(owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        Log.i("Log", "onDestroy: ")
        super.onDestroy(owner)
    }
}