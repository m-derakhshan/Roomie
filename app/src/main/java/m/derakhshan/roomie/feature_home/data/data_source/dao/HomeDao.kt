package m.derakhshan.roomie.feature_home.data.data_source.dao


import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import m.derakhshan.roomie.feature_property.domain.model.PropertyModel

@Dao
interface HomeDao {

    @Query("SELECT * FROM PropertyModel WHERE isSpecial='1' ")
    fun getSpecialPlaces(): Flow<List<PropertyModel>>

}