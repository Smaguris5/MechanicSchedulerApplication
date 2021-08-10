package com.ar1246.mechanicschedulerapplication.data.db

import androidx.room.TypeConverter
import java.util.*
//Required to convert Date object into Long as Room database cannot store Date
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}