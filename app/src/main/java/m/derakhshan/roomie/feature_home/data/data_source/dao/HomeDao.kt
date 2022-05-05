package m.derakhshan.roomie.feature_home.data.data_source.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import m.derakhshan.roomie.feature_home.domain.model.PropertyModel

@Dao
interface HomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaces(item: List<PropertyModel>)

    @Query("DELETE FROM PropertyModel")
    suspend fun deleteAllPlaces()

    @Query("SELECT * FROM PropertyModel")
    fun getAllPlaces(): Flow<List<PropertyModel>>

    @Query("SELECT * FROM PropertyModel WHERE isSpecial='1' ")
    fun getSpecialPlaces(): Flow<List<PropertyModel>>

}