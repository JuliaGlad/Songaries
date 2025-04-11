package myapplication.android.musicplayerapp.data.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import myapplication.android.musicplayerapp.data.api.models.Track


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

class IntegerListConverter : ListConverter<Track>(object : TypeToken<MutableList<Track>>(){})