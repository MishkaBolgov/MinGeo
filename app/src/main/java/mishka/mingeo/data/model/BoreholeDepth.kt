package mishka.mingeo.data.model


import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import org.joda.time.DateTime

import org.joda.time.Instant
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat

@Entity
@TypeConverters(DateConverter::class)
class BoreholeDepth : DatabaseEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var depth: Float = 0F

    var boreholeId: Int = 0

    var date: Instant? = null


    @Ignore
    val HOURS_IN_DAY = 24
    @Ignore
    val MINUTES_IN_HOUR = 60
    @Ignore
    val SECONDS_IN_MINUTE = 60
    @Ignore
    val SECONDS_IN_DAY = HOURS_IN_DAY * MINUTES_IN_HOUR * SECONDS_IN_MINUTE.toFloat()

    val days: Float
        get() {
            val dateTime = LocalDateTime(date)
            val secondsOfDay: Float = dateTime.hourOfDay.hoursToSeconds() + dateTime.minuteOfHour.minutesToSeconds() + dateTime.secondOfMinute

            return dateTime.dayOfYear + (secondsOfDay / SECONDS_IN_DAY)
        }

    private fun Int.hoursToSeconds(): Float {
        return this * MINUTES_IN_HOUR * SECONDS_IN_MINUTE.toFloat()
    }

    private fun Int.minutesToSeconds(): Float {
        return this * SECONDS_IN_MINUTE.toFloat()
    }

    fun getFormatDate(): String {
        val dateTime = DateTime(date)
        val formatter = DateTimeFormat.forPattern("HH:mm | dd.MM.yy")
//        return "${dateTime.hourOfDay}:${dateTime.minuteOfHour} | ${dateTime.dayOfMonth().asText}.${dateTime.monthOfYear().asText}"
        return formatter.print(date)
    }

    constructor() {}

    constructor(borehole: Borehole, depth: Float) {
        this.depth = depth
        this.boreholeId = borehole.getId()
        date = Instant.now()
    }

    fun relativeDepth(originDepth: Float): Float = depth - originDepth
}
