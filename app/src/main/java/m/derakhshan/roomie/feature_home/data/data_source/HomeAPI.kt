package m.derakhshan.roomie.feature_home.data.data_source

import m.derakhshan.roomie.feature_home.data.data_source.dto.PlaceModelServerResponse
import retrofit2.http.GET

interface HomeAPI {
    @GET("special_places")
    suspend fun getSpecialPlaces(): List<PlaceModelServerResponse>
}