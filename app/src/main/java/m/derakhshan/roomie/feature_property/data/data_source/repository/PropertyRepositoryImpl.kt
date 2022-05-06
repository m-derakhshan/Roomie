package m.derakhshan.roomie.feature_property.data.data_source.repository


import m.derakhshan.roomie.feature_property.data.data_source.dao.PropertyDao
import m.derakhshan.roomie.feature_property.domain.model.PropertyModel
import m.derakhshan.roomie.feature_property.domain.repository.PropertyRepository

class PropertyRepositoryImpl(private val database: PropertyDao) : PropertyRepository {

    override suspend fun getPropertyById(propertyId: String): PropertyModel {
        return database.getPropertyById(id = propertyId)
    }

}