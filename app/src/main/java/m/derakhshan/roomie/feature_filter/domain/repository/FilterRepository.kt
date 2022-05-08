package m.derakhshan.roomie.feature_filter.domain.repository

import kotlinx.coroutines.flow.Flow
import m.derakhshan.roomie.feature_filter.domain.model.AppliedFilterModel
import m.derakhshan.roomie.feature_filter.domain.model.FilterModel

interface FilterRepository {

    suspend fun updateAppliedFilter(appliedFilterModel: AppliedFilterModel)
    fun getAppliedFilter(): Flow<AppliedFilterModel?>
    suspend fun getFilters(): FilterModel

}