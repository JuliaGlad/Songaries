package myapplication.android.musicplayerapp.data.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


abstract class ListConverter<T>(
    private val typeToken: TypeToken<MutableList<T>>
) {
    private val gson = Gson()
    @TypeConverter
    fun toJson(value: MutableList<T>): String {
        return gson.toJson(value, typeToken.type)
    }

    @TypeConverter
    fun fromJson(json: String):MutableList<T> {
        return gson.fromJson(json, typeToken)
    }
}

class IntegerListConverter : ListConverter<Int>(object : TypeToken<MutableList<Int>>(){})