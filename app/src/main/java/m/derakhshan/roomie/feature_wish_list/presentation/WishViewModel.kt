package m.derakhshan.roomie.feature_wish_list.presentation


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import m.derakhshan.roomie.feature_property.domain.model.PropertyModel
import m.derakhshan.roomie.feature_wish_list.domain.repository.WishRepository
import javax.inject.Inject

@HiltViewModel
class WishViewModel @Inject constructor(repository: WishRepository) : ViewModel() {

    private var _wishes = mutableStateOf<List<PropertyModel>>(emptyList())
    val wishes: State<List<PropertyModel>> = _wishes

    init {
        viewModelScope.launch {
            repository.getWishList().collectLatest { _wishes.value = it }
        }
    }


}