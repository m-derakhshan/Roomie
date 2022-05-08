package m.derakhshan.roomie.feature_filter.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import m.derakhshan.roomie.feature_property.domain.model.EquipmentModel
import m.derakhshan.roomie.feature_property.domain.model.PropertyFeatureModel
import m.derakhshan.roomie.feature_property.domain.model.PropertyTypeModel

@Entity
data class FilterModel(
    @PrimaryKey
    val id: Int = 1,
    val priceRangeLimit: ClosedFloatingPointRange<Float>,
    val propertyType: List<PropertyTypeModel>,
    val equipments: List<EquipmentModel>,
    val propertyFeatures: List<PropertyFeatureModel>
)
