package id.t12uetatay.room.utils

import android.text.format.DateFormat
import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }

    fun getDateTime(date: Date?): String? {
        var dateTime = "-"
        if (date != null) {
            val day = DateFormat.format("dd", date) as String // 20
            val monthString = DateFormat.format("MMM", date) as String // Jun
            val yearString = DateFormat.format("yyyy", date) as String
            dateTime = "$day-$monthString-$yearString"
        }
        return dateTime
    }

}