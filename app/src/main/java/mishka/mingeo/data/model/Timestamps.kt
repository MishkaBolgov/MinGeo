package mishka.mingeo.data.model

import org.joda.time.Instant

class Timestamps {
    var createdAt: Instant? = null
    var updatedAt: Instant? = null

    init {
        createdAt = Instant.now()
        updatedAt = Instant.now()
    }

   fun updated(){
       updatedAt = Instant.now()
    }

}