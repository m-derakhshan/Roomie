package m.derakhshan.roomie.feature_property.data.data_source.repository


import m.derakhshan.roomie.core.data.data_source.ApplicationDatabase
import m.derakhshan.roomie.feature_property.data.data_source.dao.PropertyDao
import m.derakhshan.roomie.feature_property.domain.model.PropertyModel
import m.derakhshan.roomie.feature_property.domain.repository.PropertyRepository
import m.derakhshan.roomie.feature_wish_list.domain.model.WishModel

class PropertyRepositoryImpl(private val database: ApplicationDatabase) : PropertyRepository {

    override suspend fun getPropertyById(propertyId: String): PropertyModel {
        return database.propertyDao.getPropertyById(id = propertyId).copy(
            isInWishList = database.wishDao.isInWishList(propertyId = propertyId) != null
        )
    }

    override suspend fun addToWishList(propertyId: String) {
        database.wishDao.insert(propertyId = WishModel(id = propertyId))
    }

    override suspend fun removeFromWishList(propertyId: String) {
        database.wishDao.deleteFromWishes(propertyId = propertyId)
    }

}