package m.derakhshan.roomie.core.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import m.derakhshan.roomie.feature_home.data.data_source.dao.HomeDao
import m.derakhshan.roomie.feature_home.domain.model.PlaceModel


@Database(
    entities = [PlaceModel::class],
    version = 1
)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract val homeDao: HomeDao
}