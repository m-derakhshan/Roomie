package m.derakhshan.roomie.feature_filter.presentation


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import m.derakhshan.roomie.feature_filter.domain.model.AppliedFilterModel
import m.derakhshan.roomie.feature_filter.domain.model.toDate
import m.derakhshan.roomie.feature_filter.domain.repository.FilterRepository
import javax.inject.Inject


@HiltViewModel
class FilterViewModel @Inject constructor(private val repository: FilterRepository) : ViewModel() {

    private val _state = mutableStateOf(FilterState())
    val state: State<FilterState> = _state

    private var appliedFilterModel: AppliedFilterModel = AppliedFilterModel()

    init {
        viewModelScope.launch {
            with(repository.getFilters()) {
                _state.value = _state.value.copy(
                    priceRangeLimit = this.priceRangeLimit,
                    propertyType = this.propertyType,
                    equipments = this.equipments,
                    propertyFeatures = this.propertyFeatures
                )
            }
            repository.getAppliedFilter().collectLatest {
                appliedFilterModel =
                    it ?: AppliedFilterModel(priceRange = _state.value.priceRangeLimit)
               //--------------------(make sure price range is in the range even after resetting filters)--------------------//
                appliedFilterModel = appliedFilterModel.copy(
                    priceRange = if (appliedFilterModel.priceRange == 0f..1f)
                        _state.value.priceRangeLimit
                    else
                        appliedFilterModel.priceRange
                )
                _state.value = _state.value.copy(
                    searchValue = appliedFilterModel.search,
                    selectedPropertyTypeId = appliedFilterModel.selectedPropertyTypeId,
                    priceRange = appliedFilterModel.priceRange,
                    availableFrom = appliedFilterModel.availableFrom.toDate(),
                )
            }
        }
    }

    fun onEvent(event: FilterEvent) {
        when (event) {
            is FilterEvent.SearchValueChange -> {
                _state.value = _state.value.copy(searchValue = event.search)
                appliedFilterModel = appliedFilterModel.copy(
                    search = event.search
                )
            }
            is FilterEvent.UpdateSelectedEquipment -> {
                val index = _state.value.equipments.indexOfFirst { it.id == event.equipment.id }
                val newList = _state.value.equipments.toMutableList()
                newList[index] = event.equipment
                _state.value = _state.value.copy(equipments = newList)
                appliedFilterModel = appliedFilterModel.copy(equipments = newList.map { it.id })
            }
            is FilterEvent.UpdateSelectedPropertyType -> {
                _state.value = _state.value.copy(selectedPropertyTypeId = event.type.id)
                appliedFilterModel = appliedFilterModel.copy(selectedPropertyTypeId = event.type.id)
            }
            is FilterEvent.UpdateAvailableFrom -> {
                _state.value = _state.value.copy(availableFrom = event.date)
                appliedFilterModel = appliedFilterModel.copy(availableFrom = event.date.toString())
            }
            is FilterEvent.UpdatePriceRange -> {
                _state.value = _state.value.copy(priceRange = event.range)
                appliedFilterModel =
                    appliedFilterModel.copy(priceRange = event.range)
            }
            is FilterEvent.UpdatePropertyFeature -> {
                val index = _state.value.propertyFeatures.indexOfFirst { it.id == event.feature.id }
                val newList = _state.value.propertyFeatures.toMutableList()
                newList[index] = event.feature.copy(
                    value = (_state.value.propertyFeatures[index].value + (when {
                        event.add -> 1
                        _state.value.propertyFeatures[index].value > 0 -> -1
                        else -> 0
                    }))
                )
                _state.value = _state.value.copy(
                    propertyFeatures = newList
                )
                appliedFilterModel = appliedFilterModel.copy(
                    propertyFeatures = newList.map { it.id }
                )
            }
            is FilterEvent.ConfirmAppliedFilter -> {
                viewModelScope.launch {
                    if (!event.confirm)
                        appliedFilterModel = AppliedFilterModel()
                    repository.updateAppliedFilter(appliedFilterModel)
                }
            }
        }
    }
}