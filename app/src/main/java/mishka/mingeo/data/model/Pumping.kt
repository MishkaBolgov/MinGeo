package mishka.mingeo.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import org.joda.time.Instant
import java.io.Serializable

@Entity
class Pumping: Serializable, DatabaseEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var pumpPower: Float = 0f

//    val createdAt: Instant? = null
}
