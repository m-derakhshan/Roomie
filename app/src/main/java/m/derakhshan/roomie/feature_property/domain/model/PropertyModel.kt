package m.derakhshan.roomie.feature_property.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import m.derakhshan.roomie.feature_filter.domain.model.DateModel
import java.lang.reflect.Type


@Entity
data class PropertyModel(
    @PrimaryKey
    val id: String = "",
    val type: PropertyTypeModel = PropertyTypeModel(),
    val rent: String = "",
    val expenses: String = "",
    val images: List<String> = emptyList(),
    val address: String = "",
    val description: String = "",
    val availableFrom: DateModel = DateModel(),
    val equipments: List<EquipmentModel> = emptyList(),
    val isSpecial: Boolean = false,
    val propertyFeatures: List<PropertyFeatureModel> = emptyList(),
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

class PropertyFeaturesConverter {
    @TypeConverter
    fun fromPropertyFeatureList(listType: List<PropertyFeatureModel>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<PropertyFeatureModel>>() {}.type
        return gson.toJson(listType, type)
    }

    @TypeConverter
    fun toPropertyFeatureList(stringType: String): List<PropertyFeatureModel> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<PropertyFeatureModel>>() {}.type
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
    fun fromDate(date: DateModel): String {
        return "${date.year}_${date.month}_${date.day}"
    }

    @TypeConverter
    fun toDate(stringType: String): DateModel {
        val date = stringType.split("_")
        return DateModel(
            year = date[0].toInt(),
            month = date[1].toInt(),
            day = date[2].toInt()
        )
    }
}

class PropertyTypeConverter{
    @TypeConverter
    fun fromPropertyType(propertyType: PropertyTypeModel): String {
        val gson = Gson()
        val type: Type = object : TypeToken<PropertyTypeModel>() {}.type
        return gson.toJson(propertyType, type)
    }

    @TypeConverter
    fun toPropertyType(stringType: String): PropertyTypeModel {
        val gson = Gson()
        val type: Type = object : TypeToken<PropertyTypeModel>() {}.type
        return gson.fromJson(stringType, type)
    }

}