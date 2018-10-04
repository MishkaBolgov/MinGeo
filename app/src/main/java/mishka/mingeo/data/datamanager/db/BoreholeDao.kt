package mishka.mingeo.data.datamanager.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

import mishka.mingeo.data.model.Borehole

@Dao
interface BoreholeDao {

    @Query("SELECT * FROM borehole WHERE id=:id")
    fun getById(id: Long): Borehole

    @Insert
    fun insertBorehole(borehole: Borehole): Long

    @Query("SELECT * FROM borehole WHERE pumpingId=:pumpingId")
    fun getByPumping(pumpingId: Long): List<Borehole>

    @Query("UPDATE borehole SET distanceFromCentral=:distance WHERE id=:id")
    fun updateDistance(id: Long, distance: Float)

    @Query("UPDATE borehole SET initialDepth=:value WHERE id=:id")
    fun updateInitialDepth(id: Long, value: Float)

//    Async

    @Query("SELECT * FROM borehole WHERE pumpingId=:pumpingId")
    fun getByPumpingAsync(pumpingId: Long): LiveData<List<Borehole>>

    @Query("SELECT * FROM borehole WHERE id=:id")
    fun getByIdAsync(id: Long): LiveData<Borehole>


    @Query("UPDATE borehole SET headHeight=:value WHERE id=:id")
    fun updateHeadHeight(id: Long, value: Float)

}
