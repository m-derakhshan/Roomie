package m.derakhshan.roomie.feature_property.domain.model


data class PropertyFeatureModel(
    val id: String,
    val text: String,
    val value: Int,
    val maxValue: Int
)