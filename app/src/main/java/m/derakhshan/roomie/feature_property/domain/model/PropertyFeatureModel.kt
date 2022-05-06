package m.derakhshan.roomie.feature_property.domain.model

import com.google.gson.annotations.SerializedName

data class PropertyFeatureModel(
    val id: String = "",
    val value: String = "",
    val text: String = "",
    @SerializedName("other_info")
    val otherInfo: String = "",
)
