package m.derakhshan.roomie.feature_home.data.data_source.dto

import com.google.gson.annotations.SerializedName
import m.derakhshan.roomie.feature_home.domain.model.PlaceModel

data class PlaceModelServerResponse(
    val id: String,
    val price: String,
    val image: String,
    val address: String,
    val title: String,
    @SerializedName("is_special")
    val isSpecial: Boolean
)

fun PlaceModelServerResponse.toSpecialPlaceModel(): PlaceModel {
    return PlaceModel(
        id = this.id,
        price = "${this.price}€",
        image = this.image,
        address = this.address,
        title = this.title,
        isSpecial = this.isSpecial
    )
}
