package m.derakhshan.roomie.core

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _removeSplashScreen = mutableStateOf(false)
    val removeSplashScreen: State<Boolean> = _removeSplashScreen
    fun removeSplashScreen() {
        _removeSplashScreen.value = true
    }
}