package m.derakhshan.roomie.feature_home.data.data_source.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import m.derakhshan.roomie.feature_home.domain.model.PlaceModel

@Dao
interface HomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaces(item: List<PlaceModel>)

    @Query("DELETE FROM PlaceModel")
    suspend fun deleteAllPlaces()

    @Query("SELECT * FROM PlaceModel")
    fun getAllPlaces(): Flow<List<PlaceModel>>

    @Query("SELECT * FROM PlaceModel WHERE isSpecial='1' ")
    fun getSpecialPlaces(): Flow<List<PlaceModel>>

}