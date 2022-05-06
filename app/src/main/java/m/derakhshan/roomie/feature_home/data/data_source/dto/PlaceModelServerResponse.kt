package m.derakhshan.roomie.feature_home.data.data_source.dto

import com.google.gson.annotations.SerializedName
import m.derakhshan.roomie.feature_property.domain.model.PropertyModel
import m.derakhshan.roomie.feature_filter.domain.model.DateModel
import m.derakhshan.roomie.feature_property.domain.model.EquipmentModel

data class PlaceModelServerResponse(
    val id: String,
    val price: String,
    val images: List<String>,
    val address: String,
    val description: String,
    @SerializedName("is_special")
    val isSpecial: Boolean,
    @SerializedName("available_from")
    val availableFrom: String,
    val equipments: List<EquipmentModel>
)

fun PlaceModelServerResponse.toPropertyModel(): PropertyModel {
    return PropertyModel(
        id = this.id,
        price = "${this.price}€",
        images = this.images,
        address = this.address,
        description = this.description,
        isSpecial = this.isSpecial,
        availableFrom = with(this.availableFrom) {
            val data = this.split("-")
            DateModel(
                year = data[0].toInt(),
                month = data[1].toInt(),
                day = data[2].toInt()
            )
        },
        equipments = this.equipments
    )
}
