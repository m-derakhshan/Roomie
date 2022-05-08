package m.derakhshan.roomie.feature_filter.data.data_source.dto

import com.google.gson.annotations.SerializedName
import m.derakhshan.roomie.feature_filter.domain.model.FilterModel
import m.derakhshan.roomie.feature_property.data.data_source.dto.EquipmentServerModel
import m.derakhshan.roomie.feature_property.data.data_source.dto.PropertyFeatureServerModel
import m.derakhshan.roomie.feature_property.data.data_source.dto.toEquipmentModel
import m.derakhshan.roomie.feature_property.data.data_source.dto.toPropertyFeatureModel
import m.derakhshan.roomie.feature_property.domain.model.PropertyTypeModel

data class FilterModelServerResponse(
    @SerializedName("price_range_limit")
    val priceRangeLimit: String,
    @SerializedName("property_type")
    val propertyType: List<PropertyTypeModel>,
    val equipments: List<EquipmentServerModel>,
    @SerializedName("property_features")
    val propertyFeatures: List<PropertyFeatureServerModel>
)

fun FilterModelServerResponse.toFilterModel(): FilterModel {
    return FilterModel(
        priceRangeLimit = with(this.priceRangeLimit) {
            val range = this.split("_")
            range[0].toFloat()..range[1].toFloat()
        },
        propertyType = this.propertyType,
        equipments = this.equipments.map { it.toEquipmentModel() },
        propertyFeatures = this.propertyFeatures.map { it.toPropertyFeatureModel() }
    )
}

