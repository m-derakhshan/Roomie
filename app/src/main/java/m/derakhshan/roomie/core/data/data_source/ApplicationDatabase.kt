package m.derakhshan.roomie.core.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import m.derakhshan.roomie.feature_home.data.data_source.dao.HomeDao
import m.derakhshan.roomie.feature_property.data.data_source.dao.PropertyDao
import m.derakhshan.roomie.feature_property.domain.model.*


@Database(
    entities = [PropertyModel::class],
    version = 1,
)
@TypeConverters(
    EquipmentsListConverter::class, StringListConverter::class, DateConverter::class,
    PropertyFeaturesConverter::class
)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract val homeDao: HomeDao
    abstract val propertyDao: PropertyDao
}