package mishka.mingeo.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters
import org.joda.time.Instant
import java.io.Serializable

@Entity
@TypeConverters(DateConverter::class)
class Pumping: Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    var pumpPower: Float = 0f

    var centralBoreholeId: Long? = null

    var startPumpingTime: Instant? = null

}
