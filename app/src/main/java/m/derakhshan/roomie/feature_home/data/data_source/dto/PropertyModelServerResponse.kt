package m.derakhshan.roomie.feature_home.data.data_source.dto

import com.google.gson.annotations.SerializedName
import m.derakhshan.roomie.feature_filter.domain.model.toDate
import m.derakhshan.roomie.feature_property.domain.model.EquipmentModel
import m.derakhshan.roomie.feature_property.domain.model.PropertyFeatureModel
import m.derakhshan.roomie.feature_property.domain.model.PropertyModel
import m.derakhshan.roomie.feature_property.domain.model.PropertyTypeModel

data class PropertyModelServerResponse(
    val id: String,
    val type: PropertyTypeModel,
    val rent: String,
    val expenses: String,
    val images: List<String>,
    val address: String,
    val description: String,
    @SerializedName("available_from")
    val availableFrom: String,
    @SerializedName("property_features")
    val propertyFeatures: List<PropertyFeatureModel>,
    val equipments: List<EquipmentModel>,
    @SerializedName("is_special")
    val isSpecial: Boolean,
)

fun PropertyModelServerResponse.toPropertyModel(): PropertyModel {
    return PropertyModel(
        id = this.id,
        type = this.type,
        rent = "${this.rent}€",
        expenses = "${this.expenses}€",
        images = this.images,
        address = this.address,
        description = this.description,
        isSpecial = this.isSpecial,
        availableFrom = this.availableFrom.toDate(),
        equipments = this.equipments,
        propertyFeatures = this.propertyFeatures
    )
}
