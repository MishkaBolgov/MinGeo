package mishka.mingeo.data.datamanager.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

import mishka.mingeo.data.model.Pumping

@Dao
interface PumpingDao {

    @get:Query("SELECT * FROM pumping")
    val all: List<Pumping>

    @get:Query("SELECT * FROM pumping")
    val allPumpings: LiveData<List<Pumping>>


    @Query("SELECT * FROM pumping WHERE id=:id")
    fun getPumpingById(id: Int): Pumping

    @Query("UPDATE pumping SET pumppower=:power WHERE id=:pumpingId")
    fun updatePumpPowerForPumping(pumpingId: Int, power: Float)

    @Query("SELECT * FROM pumping WHERE id=:id")
    fun getLivePumpingById(id: Int): LiveData<Pumping>



//    Refactored
    @Query("SELECT * FROM pumping")
    fun all(): LiveData<List<Pumping>>

    @Insert
    fun insertPumping(pumping: Pumping): Long

}
