package m.derakhshan.roomie.feature_home.domain.repository

import kotlinx.coroutines.flow.Flow
import m.derakhshan.roomie.core.model.Response
import m.derakhshan.roomie.feature_home.domain.model.PropertyModel

interface HomeRepository {
    suspend fun updateLocalDatabase(): Response<String?>
    fun getSpecialPlaces(): Flow<List<PropertyModel>>
    fun getAllPlaces(): Flow<List<PropertyModel>>
}