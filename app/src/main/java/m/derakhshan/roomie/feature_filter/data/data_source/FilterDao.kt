package m.derakhshan.roomie.feature_filter.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import m.derakhshan.roomie.feature_filter.domain.model.AppliedFilterModel
import m.derakhshan.roomie.feature_filter.domain.model.FilterModel

@Dao
interface FilterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFilters(filters: FilterModel)

    @Query("SELECT * FROM FilterModel LIMIT 1")
    suspend fun getFilters(): FilterModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAppliedFilter(appliedFilterModel: AppliedFilterModel)

    @Query("SELECT * FROM AppliedFilterModel LIMIT 1")
    suspend fun getAppliedFilter(): AppliedFilterModel?

    @Query("DELETE FROM AppliedFilterModel")
    suspend fun deleteAll()
}