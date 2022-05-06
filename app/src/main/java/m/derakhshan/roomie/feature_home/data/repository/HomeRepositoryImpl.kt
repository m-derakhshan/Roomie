package m.derakhshan.roomie.feature_home.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import m.derakhshan.roomie.core.data.data_source.ApplicationDatabase
import m.derakhshan.roomie.core.model.Response
import m.derakhshan.roomie.feature_home.data.data_source.HomeAPI
import m.derakhshan.roomie.feature_home.data.data_source.dto.toPropertyModel
import m.derakhshan.roomie.feature_property.domain.model.PropertyModel
import m.derakhshan.roomie.feature_home.domain.repository.HomeRepository

class HomeRepositoryImpl(private val database: ApplicationDatabase, private val homeAPI: HomeAPI) :
    HomeRepository {

    override suspend fun updateLocalDatabase(): Response<String?> {
        return try {
            val places = homeAPI.updatePlaces()
            database.propertyDao.insertProperties(places.map { it.toPropertyModel() })
            Response.Success(data = "updated")
        } catch (e: Exception) {
            Log.i("Log", "updateLocalDatabaseError: ${e.message}")
            Response.Error("error")
        }

    }

    override fun getSpecialPlaces(): Flow<List<PropertyModel>> =
        database.homeDao.getSpecialPlaces()

}