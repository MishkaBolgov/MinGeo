package mishka.mingeo.data.model


import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import org.joda.time.DateTime

import org.joda.time.Instant
import org.joda.time.format.DateTimeFormat
import java.time.format.DateTimeFormatter

@Entity
@TypeConverters(DateConverter::class)
class BoreholeDepth : DatabaseEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var depth: Float = 0F

    var boreholeId: Int = 0


    var date: Instant? = null

    val minutes: Float
        get() = date!!.toDateTime().secondOfDay.toFloat()

    val monthDayDate: String
        get() = date!!.toDateTime().toString("dd/mm")

    fun getFormatDate(): String{
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
