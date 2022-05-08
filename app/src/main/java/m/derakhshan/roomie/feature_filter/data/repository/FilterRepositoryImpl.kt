package m.derakhshan.roomie.feature_filter.data.repository

import kotlinx.coroutines.flow.Flow
import m.derakhshan.roomie.feature_filter.data.data_source.FilterDao
import m.derakhshan.roomie.feature_filter.domain.model.AppliedFilterModel
import m.derakhshan.roomie.feature_filter.domain.model.FilterModel
import m.derakhshan.roomie.feature_filter.domain.repository.FilterRepository

class FilterRepositoryImpl(private val db: FilterDao) : FilterRepository {

    override suspend fun updateAppliedFilter(appliedFilterModel: AppliedFilterModel) {
        db.updateAppliedFilter(appliedFilterModel)
    }

    override fun getAppliedFilter(): Flow<AppliedFilterModel?> = db.getAppliedFilter()


    override suspend fun getFilters(): FilterModel = db.getFilters()


}