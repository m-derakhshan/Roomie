package m.derakhshan.roomie.feature_property.domain.repository

import m.derakhshan.roomie.feature_property.domain.model.PropertyModel

interface PropertyRepository {
    suspend fun getPropertyById(propertyId: String): PropertyModel
    suspend fun addToWishList(propertyId: String)
    suspend fun removeFromWishList(propertyId: String)
}