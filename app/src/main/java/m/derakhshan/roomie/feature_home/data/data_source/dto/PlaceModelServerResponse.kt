package m.derakhshan.roomie.feature_home.data.data_source.dto

import com.google.gson.annotations.SerializedName
import m.derakhshan.roomie.feature_home.domain.model.PropertyModel
import m.derakhshan.roomie.feature_home.domain.model.date.Date
import m.derakhshan.roomie.feature_home.domain.model.filter.EquipmentModel

data class PlaceModelServerResponse(
    val id: String,
    val price: String,
    val images: List<String>,
    val address: String,
    val title: String,
    @SerializedName("is_special")
    val isSpecial: Boolean,
    @SerializedName("available_from")
    val availableFrom: String,
    val equipments: List<EquipmentModel>
)

fun PlaceModelServerResponse.toSpecialPlaceModel(): PropertyModel {
    return PropertyModel(
        id = this.id,
        price = "${this.price}€",
        images = this.images,
        address = this.address,
        title = this.title,
        isSpecial = this.isSpecial,
        availableFrom = with(this.availableFrom) {
            val data = this.split("-")
            Date(
                year = data[0].toInt(),
                month = data[1].toInt(),
                day = data[2].toInt()
            )
        },
        equipments = this.equipments
    )
}
