package m.derakhshan.roomie.feature_home.data.data_source

import m.derakhshan.roomie.feature_home.data.data_source.dto.PlaceModelServerResponse
import retrofit2.http.GET

interface HomeAPI {

    @GET("places")
    suspend fun updatePlaces(): List<PlaceModelServerResponse>

    @GET("filters")
    suspend fun updateFilters(): List<PlaceModelServerResponse>

}