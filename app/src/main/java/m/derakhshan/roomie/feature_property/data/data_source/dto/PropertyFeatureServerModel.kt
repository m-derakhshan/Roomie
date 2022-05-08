package m.derakhshan.roomie.feature_property.data.data_source.dto

import com.google.gson.annotations.SerializedName
import m.derakhshan.roomie.feature_property.domain.model.PropertyFeatureModel

data class PropertyFeatureServerModel(
    val id: String,
    val text: String,
    @SerializedName("max_value")
    val maxValue: Int
)

fun PropertyFeatureServerModel.toPropertyFeatureModel(): PropertyFeatureModel {
    return PropertyFeatureModel(
        id = this.id,
        text = this.text,
        value = 0,
        maxValue = this.maxValue
    )
}
