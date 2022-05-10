package m.derakhshan.roomie.feature_wish_list.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class WishModel(
    @PrimaryKey
    val id: String
)
