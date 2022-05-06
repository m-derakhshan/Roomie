package m.derakhshan.roomie.feature_filter.presentation


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import m.derakhshan.roomie.feature_property.domain.model.EquipmentModel
import m.derakhshan.roomie.feature_property.domain.model.PropertyFeatureModel
import m.derakhshan.roomie.feature_property.domain.model.PropertyTypeModel


class FilterViewModel : ViewModel() {

    private val _state = mutableStateOf(FilterState())
    val state: State<FilterState> = _state


    init {
        _state.value = _state.value.copy(
            propertyType = listOf(
                PropertyTypeModel(
                    id = "0",
                    icon = "https://cdn0.iconfinder.com/data/icons/real-estate-blue-line/64/130-RealEstate-Blue_real-estate-building-apartment-house-512.png",
                    title = "All Properties"
                ),
                PropertyTypeModel(
                    id = "1",
                    icon = "https://cdn0.iconfinder.com/data/icons/real-estate-blue-line/64/130-RealEstate-Blue_real-estate-building-apartment-house-512.png",
                    title = "Single Room"
                ),
                PropertyTypeModel(
                    id = "22",
                    icon = "https://cdn0.iconfinder.com/data/icons/real-estate-blue-line/64/130-RealEstate-Blue_real-estate-building-apartment-house-512.png",
                    title = "Bed"
                ),
                PropertyTypeModel(
                    id = "3",
                    icon = "https://cdn0.iconfinder.com/data/icons/real-estate-blue-line/64/130-RealEstate-Blue_real-estate-building-apartment-house-512.png",
                    title = "Studio-Flat"
                )
            ),
            equipments = listOf(
                EquipmentModel(
                    id = "1",
                    title = "TV",
                    isChecked = false
                ),
                EquipmentModel(
                    id = "2",
                    title = "Balcony",
                    isChecked = false
                ),
                EquipmentModel(
                    id = "3",
                    title = "Elevator",
                    isChecked = false
                ),
                EquipmentModel(
                    id = "4",
                    title = "Garden",
                    isChecked = false
                ),
                EquipmentModel(
                    id = "5",
                    title = "TV Signal",
                    isChecked = false
                ),
                EquipmentModel(
                    id = "6",
                    title = "Dish washer",
                    isChecked = false
                ),
                EquipmentModel(
                    id = "7",
                    title = "Private bathroom",
                    isChecked = false
                ),
                EquipmentModel(
                    id = "8",
                    title = "Storage room",
                    isChecked = false
                ),
                EquipmentModel(
                    id = "9",
                    title = "Wifi",
                    isChecked = false
                ),
                EquipmentModel(
                    id = "10",
                    title = "Reception",
                    isChecked = false
                ),
                EquipmentModel(
                    id = "11",
                    title = "Washing machine",
                    isChecked = false
                ),
                EquipmentModel(
                    id = "12",
                    title = "Air conditioner",
                    isChecked = false
                ),
                EquipmentModel(
                    id = "13",
                    title = "Bike parking",
                    isChecked = false
                )
            ),
            propertyFeatures = listOf(
                PropertyFeatureModel(
                    id = "0",
                    "Persons Number"
                ),
                PropertyFeatureModel(
                    id = "1",
                    "Rooms Number"
                ),
                PropertyFeatureModel(
                    id = "2",
                    "Beds Number"
                )
            )
        )
    }

    fun onEvent(event: FilterEvent) {
        when (event) {
            is FilterEvent.SearchValueChange -> {
                _state.value = _state.value.copy(searchValue = event.search)
            }
            is FilterEvent.UpdateSelectedEquipment -> {
                val index = _state.value.equipments.indexOfFirst { it.id == event.equipment.id }
                val newList = _state.value.equipments.toMutableList()
                newList[index] = event.equipment
                _state.value = _state.value.copy(
                    equipments = newList
                )
            }
            is FilterEvent.UpdateSelectedPropertyType -> {
                _state.value = _state.value.copy(selectedPropertyType = event.type)
            }
            is FilterEvent.UpdateAvailableFrom -> {
                _state.value = _state.value.copy(availableFrom = event.date)
            }
            is FilterEvent.UpdatePriceRange -> {
                _state.value = _state.value.copy(priceRange = event.range)
            }
            FilterEvent.ResetAllFilters -> {
                // TODO: implement reset filters
            }
            is FilterEvent.UpdatePropertyFeature -> {
                val index = _state.value.propertyFeatures.indexOfFirst { it.id == event.feature.id }
                val newList = _state.value.propertyFeatures.toMutableList()
                newList[index] = event.feature.copy(
                    number = _state.value.propertyFeatures[index].number + (
                            when {
                                event.add -> 1
                                _state.value.propertyFeatures[index].number > 0 -> -1
                                else -> 0
                            }
                            )
                )
                _state.value = _state.value.copy(
                    propertyFeatures = newList
                )
            }
        }
    }
}