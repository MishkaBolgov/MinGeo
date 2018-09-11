package mishka.mingeo.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Note() {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    var pumpingId: Long = 0
    var path = ""

    constructor(path: String, pumping: Pumping) : this() {
        pumpingId = pumping.id.toLong()
        this.path = path
    }
}