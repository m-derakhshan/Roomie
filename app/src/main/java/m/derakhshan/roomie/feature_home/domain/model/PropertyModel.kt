package m.derakhshan.roomie.feature_home.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import m.derakhshan.roomie.feature_home.domain.model.date.Date
import m.derakhshan.roomie.feature_home.domain.model.filter.EquipmentModel
import java.lang.reflect.Type


@Entity
data class PropertyModel(
    @PrimaryKey
    val id: String,
    val price: String,
    val images: List<String>,
    val title: String,
    val address: String,
    val isSpecial: Boolean,
    val availableFrom: Date,
    val equipments: List<EquipmentModel>
)

class EquipmentsListConverter {
    @TypeConverter
    fun fromEquipmentsList(listType: List<EquipmentModel>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<EquipmentModel>>() {}.type
        return gson.toJson(listType, type)
    }

    @TypeConverter
    fun toEquipmentsList(stringType: String): List<EquipmentModel> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<EquipmentModel>>() {}.type
        return gson.fromJson(stringType, type)
    }
}


class StringListConverter {
    @TypeConverter
    fun fromStringList(listType: List<String>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(listType, type)
    }

    @TypeConverter
    fun toStringList(stringType: String): List<String> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(stringType, type)
    }
}

class DateConverter {
    @TypeConverter
    fun fromDate(date: Date): String {
        return "${date.year}_${date.month}_${date.day}"
    }

    @TypeConverter
    fun toDate(stringType: String): Date {
        val date = stringType.split("_")
        return Date(
            year = date[0].toInt(),
            month = date[1].toInt(),
            day = date[2].toInt()
        )
    }
}