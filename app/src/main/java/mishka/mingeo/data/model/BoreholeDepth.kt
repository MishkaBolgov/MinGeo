package mishka.mingeo.data.model


import android.arch.persistence.room.*
import org.joda.time.DateTime

import org.joda.time.Instant
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import kotlin.math.log10

@Entity
@TypeConverters(DateConverter::class)
class BoreholeDepth {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    var depth: Float = 0F

    var boreholeId: Long = 0

    @Embedded
    var timestamps: Timestamps = Timestamps()

    @Ignore
    val HOURS_IN_DAY = 24
    @Ignore
    val MINUTES_IN_HOUR = 60
    @Ignore
    val SECONDS_IN_MINUTE = 60
    @Ignore
    val MILLIS_IN_SECOND = 1000
    @Ignore
    val SECONDS_IN_DAY = HOURS_IN_DAY *
            MINUTES_IN_HOUR *
            SECONDS_IN_MINUTE.toFloat()

    @Ignore
    val MILLIS_IN_DAY = HOURS_IN_DAY *
            MINUTES_IN_HOUR *
            SECONDS_IN_MINUTE *
            MILLIS_IN_SECOND.toFloat()

    val days: Float
        get() {
            val dateTime = LocalDateTime(timestamps.createdAt)
            val secondsOfDay: Float = dateTime.hourOfDay.hoursToSeconds() + dateTime.minuteOfHour.minutesToSeconds() + dateTime.secondOfMinute

            return dateTime.dayOfYear + (secondsOfDay / SECONDS_IN_DAY)
        }

    fun daysFrom(from: Instant): Float {
        val period = timestamps.createdAt!!.millis - from.millis
        val result = period / MILLIS_IN_DAY
        return result
    }

//    log(t)
    fun logDaysFrom(from: Instant): Float {
        return log10(daysFrom(from))
    }

//    log(t/r)
    fun logDaysFromDividedByDistance(from: Instant, distance: Float): Float {
        return log10(daysFrom(from) / distance)
    }

    private fun Int.hoursToSeconds(): Float {
        return this * MINUTES_IN_HOUR * SECONDS_IN_MINUTE.toFloat()
    }

    private fun Int.minutesToSeconds(): Float {
        return this * SECONDS_IN_MINUTE.toFloat()
    }

    fun getFormatDate(): String {
        val dateTime = DateTime(timestamps.createdAt)
        val formatter = DateTimeFormat.forPattern("HH:mm | dd.MM.yy")
//        return "${dateTime.hourOfDay}:${dateTime.minuteOfHour} | ${dateTime.dayOfMonth().asText}.${dateTime.monthOfYear().asText}"
        return formatter.print(timestamps.createdAt)
    }

    constructor()

    constructor(borehole: Borehole, depth: Float) {
        this.depth = depth
        this.boreholeId = borehole.id
        timestamps = Timestamps()
    }

    fun relativeDepth(initialDepth: Float): Float = initialDepth - depth
}
