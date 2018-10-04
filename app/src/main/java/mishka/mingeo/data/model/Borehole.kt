package mishka.mingeo.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

import org.joda.time.Instant

import java.io.Serializable
import java.util.ArrayList
import java.util.Random

import android.arch.persistence.room.ForeignKey.CASCADE

@Entity
class Borehole : Serializable{
    @PrimaryKey(autoGenerate = true)
    var id = 0L

    var pumpingId = 0L

    var initialDepth = 0f

    var distanceFromCentral = 0f

    var headHeight = 0f

    constructor() {}

    constructor(pumping: Pumping) {
        this.pumpingId = pumping.id
    }
}
