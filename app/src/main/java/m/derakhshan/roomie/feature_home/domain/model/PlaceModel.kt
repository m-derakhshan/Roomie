package m.derakhshan.roomie.feature_home.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class PlaceModel(
    @PrimaryKey
    val id: String,
    val price: String,
    val image: String,
    val title: String,
    val address: String,
    val isSpecial: Boolean
)
