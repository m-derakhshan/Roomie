package m.derakhshan.roomie.core.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import m.derakhshan.roomie.feature_home.data.data_source.dao.HomeDao
import m.derakhshan.roomie.feature_home.domain.model.DateConverter
import m.derakhshan.roomie.feature_home.domain.model.EquipmentsListConverter
import m.derakhshan.roomie.feature_home.domain.model.PropertyModel
import m.derakhshan.roomie.feature_home.domain.model.StringListConverter


@Database(
    entities = [PropertyModel::class],
    version = 1,
)
@TypeConverters(EquipmentsListConverter::class, StringListConverter::class, DateConverter::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract val homeDao: HomeDao
}