package m.derakhshan.roomie.feature_property.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import m.derakhshan.roomie.feature_filter.domain.model.DateModel


@Entity
data class PropertyModel(
    @PrimaryKey
    val id: String = "",
    val type: PropertyTypeModel = PropertyTypeModel(),
    val rent: String = "",
    val expenses: String = "",
    val images: List<String> = emptyList(),
    val address: String = "",
    val description: String = "",
    val availableFrom: DateModel = DateModel(),
    val equipments: List<EquipmentModel> = emptyList(),
    val isSpecial: Boolean = false,
    val propertyFeatures: List<PropertyFeatureModel> = emptyList(),
)
