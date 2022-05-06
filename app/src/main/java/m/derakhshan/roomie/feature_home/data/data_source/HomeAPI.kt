package m.derakhshan.roomie.feature_home.data.data_source

import m.derakhshan.roomie.feature_home.data.data_source.dto.PropertyModelServerResponse
import retrofit2.http.GET

interface HomeAPI {

    @GET("places")
    suspend fun updatePlaces(): List<PropertyModelServerResponse>

    @GET("filters")
    suspend fun updateFilters(): List<PropertyModelServerResponse>

}