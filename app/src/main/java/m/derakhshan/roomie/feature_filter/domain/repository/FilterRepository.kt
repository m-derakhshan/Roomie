package m.derakhshan.roomie.feature_filter.domain.repository

import m.derakhshan.roomie.feature_filter.domain.model.AppliedFilterModel
import m.derakhshan.roomie.feature_filter.domain.model.FilterModel

interface FilterRepository {

    suspend fun updateAppliedFilter(appliedFilterModel: AppliedFilterModel)
    suspend fun getAppliedFilter(): AppliedFilterModel?
    suspend fun getFilters(): FilterModel

}