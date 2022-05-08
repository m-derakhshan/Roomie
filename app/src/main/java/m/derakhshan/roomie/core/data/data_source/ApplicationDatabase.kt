package m.derakhshan.roomie.core.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import m.derakhshan.roomie.core.model.*
import m.derakhshan.roomie.feature_filter.data.data_source.FilterDao
import m.derakhshan.roomie.feature_filter.domain.model.AppliedFilterModel
import m.derakhshan.roomie.feature_filter.domain.model.FilterModel
import m.derakhshan.roomie.feature_home.data.data_source.dao.HomeDao
import m.derakhshan.roomie.feature_property.data.data_source.dao.PropertyDao
import m.derakhshan.roomie.feature_property.domain.model.PropertyModel


@Database(
    entities = [PropertyModel::class, AppliedFilterModel::class, FilterModel::class],
    version = 1,
)
@TypeConverters(
    EquipmentsListConverter::class,
    StringListConverter::class,
    DateConverter::class,
    PropertyTypeListConverter::class,
    PropertyFeatureListConverter::class,
    PropertyTypeConverter::class,
    ClosedFloatRangeConverter::class
)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract val homeDao: HomeDao
    abstract val propertyDao: PropertyDao
    abstract val filterDao: FilterDao
}