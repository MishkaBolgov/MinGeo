package mishka.mingeo.data.model

import android.arch.persistence.room.TypeConverter

import org.joda.time.Instant

class DateConverter {
    @TypeConverter
    fun instantToMillis(instant: Instant?): Long = instant?.millis ?: -1

    @TypeConverter
    fun millisToInstant(millis: Long): Instant? = if (millis == -1L) null else Instant(millis)

}
