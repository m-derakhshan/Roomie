package m.derakhshan.roomie.feature_property.data.data_source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import m.derakhshan.roomie.feature_property.domain.model.PropertyModel


@Dao
interface PropertyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProperties(item: List<PropertyModel>)

    @Query("DELETE FROM PropertyModel")
    suspend fun deleteAllProperties()

    @Query("SELECT * FROM PropertyModel")
    fun getAllProperties(): Flow<List<PropertyModel>>

    @Query("SELECT * FROM PropertyModel WHERE id=:id")
    suspend fun getPropertyById(id: String): PropertyModel?

}