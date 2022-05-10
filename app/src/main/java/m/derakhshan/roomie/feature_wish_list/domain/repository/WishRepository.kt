package m.derakhshan.roomie.feature_wish_list.domain.repository

import kotlinx.coroutines.flow.Flow
import m.derakhshan.roomie.feature_property.domain.model.PropertyModel

interface WishRepository {
    fun getWishList(): Flow<List<PropertyModel>>
}