package mishka.mingeo.data.datamanager.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

import mishka.mingeo.data.model.Pumping
import org.joda.time.Instant

@Dao
interface PumpingDao {

    @Query("SELECT * FROM pumping")
    fun getAll(): List<Pumping>

    @Query("SELECT * FROM pumping WHERE id=:id")
    fun getById(id: Long): Pumping

    @Insert
    fun insert(pumping: Pumping): Long

    @Query("UPDATE pumping SET pumppower=:power WHERE id=:pumpingId")
    fun updatePumpPower(pumpingId: Long, power: Float)

    @Query("UPDATE pumping SET centralBoreholeId=:centralBoreholeId WHERE id=:pumpingId")
    fun updateCentralBorehole(centralBoreholeId: Long, pumpingId: Long)

    @Query("UPDATE pumping SET startPumpingTime=:millis WHERE id=:id")
    fun updateStartPumpingTime(id: Long, millis: Long)

//    Async
    @Query("SELECT * FROM pumping")
    fun getAllAsync(): LiveData<List<Pumping>>

    @Query("SELECT * FROM pumping WHERE id=:id")
    fun getByIdAsync(id: Long): LiveData<Pumping>


}
