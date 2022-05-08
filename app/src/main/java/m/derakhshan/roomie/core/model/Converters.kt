package m.derakhshan.roomie.core.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import m.derakhshan.roomie.feature_filter.domain.model.DateModel
import m.derakhshan.roomie.feature_property.domain.model.EquipmentModel
import m.derakhshan.roomie.feature_property.domain.model.PropertyFeatureModel
import m.derakhshan.roomie.feature_property.domain.model.PropertyTypeModel
import java.lang.reflect.Type


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

class PropertyFeatureListConverter {
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

class PropertyTypeListConverter {
    @TypeConverter
    fun fromPropertyTypeList(listType: List<PropertyTypeModel>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<PropertyTypeModel>>() {}.type
        return gson.toJson(listType, type)
    }

    @TypeConverter
    fun toPropertyTypeList(stringType: String): List<PropertyTypeModel> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<PropertyTypeModel>>() {}.type
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

class ClosedFloatRangeConverter {
    @TypeConverter
    fun fromRange(range: ClosedFloatingPointRange<Float>): String {
        return "${range.start}_${range.endInclusive}"
    }

    @TypeConverter
    fun toRange(stringType: String): ClosedFloatingPointRange<Float> {
        val range = stringType.split("_")
        return range[0].toFloat()..range[1].toFloat()
    }
}

class PropertyTypeConverter {
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