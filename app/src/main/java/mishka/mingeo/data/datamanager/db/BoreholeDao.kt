package mishka.mingeo.data.datamanager.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

import mishka.mingeo.data.model.Borehole

@Dao
interface BoreholeDao {

    @Insert
    fun createBorehole(borehole: Borehole): Long

    @Query("SELECT * FROM borehole WHERE pumpingId=:pumpingId")
    fun getBoreholesForPumping(pumpingId: Int): List<Borehole>

    @Query("SELECT * FROM borehole WHERE id=:id")
    fun getBoreholeById(id: Long): Borehole


    @Query("SELECT * FROM borehole WHERE pumpingId=:pumpingId")
    fun getLiveBoreholesForPumping(pumpingId: Int): LiveData<List<Borehole>>

    @Query("UPDATE borehole SET distancefromcentral=:distance WHERE id=:id")
    fun updateDistance(id: Int, distance: Int)
}
