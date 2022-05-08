package m.derakhshan.roomie.feature_property.data.data_source.dto

import m.derakhshan.roomie.feature_property.domain.model.EquipmentModel

data class EquipmentServerModel(
    val id: String,
    val text: String,
    val icon: String,
)

fun EquipmentServerModel.toEquipmentModel(): EquipmentModel {
    return EquipmentModel(
        id = this.id,
        text = this.text,
        icon = this.icon,
        isChecked = false
    )
}
