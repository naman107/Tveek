package com.example.data.source.local

import androidx.room.TypeConverter
import com.example.domain.models.TvShow
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Type converters to allow Room to reference complex data types.
 */
class Converters {
    @TypeConverter
    fun tvShowToString(show: TvShow.Result): String? {
        val type = object : TypeToken<TvShow.Result>() {}.type
        return Gson().toJson(show, type)
    }

    @TypeConverter
    fun stringToTvShow(tvShow: String?): TvShow.Result {
        val type = object : TypeToken<TvShow.Result>() {}.type
        return Gson().fromJson(tvShow, type)
    }
}
