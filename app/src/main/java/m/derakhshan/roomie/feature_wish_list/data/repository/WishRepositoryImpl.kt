package m.derakhshan.roomie.feature_wish_list.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import m.derakhshan.roomie.core.data.data_source.ApplicationDatabase
import m.derakhshan.roomie.feature_property.domain.model.PropertyModel
import m.derakhshan.roomie.feature_wish_list.domain.repository.WishRepository

class WishRepositoryImpl(private val database: ApplicationDatabase) : WishRepository {
    override fun getWishList(): Flow<List<PropertyModel>> {
        return with(database.wishDao.getWishList()) {
            this.map {
                database.propertyDao.getPropertyById(it.map { item -> item.id })
            }
        }

    }

}