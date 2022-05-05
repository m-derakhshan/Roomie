package m.derakhshan.roomie.feature_home.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import m.derakhshan.roomie.core.model.Response
import m.derakhshan.roomie.feature_home.data.data_source.HomeAPI
import m.derakhshan.roomie.feature_home.data.data_source.dao.HomeDao
import m.derakhshan.roomie.feature_home.data.data_source.dto.toSpecialPlaceModel
import m.derakhshan.roomie.feature_home.domain.model.PropertyModel
import m.derakhshan.roomie.feature_home.domain.repository.HomeRepository

class HomeRepositoryImpl(private val database: HomeDao, private val homeAPI: HomeAPI) :
    HomeRepository {

    override suspend fun updateLocalDatabase(): Response<String?> {
        return try {
            val places = homeAPI.getSpecialPlaces()
            database.insertPlaces(places.map { it.toSpecialPlaceModel() })
            Response.Success(data = "updated")
        } catch (e: Exception) {
            Log.i("Log", "updateLocalDatabaseError: ${e.message}")
            Response.Error("error")
        }

    }

    override fun getSpecialPlaces(): Flow<List<PropertyModel>> =
        database.getSpecialPlaces()

    override fun getAllPlaces(): Flow<List<PropertyModel>> =
        database.getAllPlaces()


}