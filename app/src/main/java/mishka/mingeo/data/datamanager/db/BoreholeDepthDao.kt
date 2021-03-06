package mishka.mingeo.data.datamanager.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

import mishka.mingeo.data.model.Borehole
import mishka.mingeo.data.model.BoreholeDepth

@Dao
interface BoreholeDepthDao {

    @Query("SELECT * FROM BoreholeDepth")
    fun getAll(): List<BoreholeDepth>

    @Insert
    fun insert(boreholeDepth: BoreholeDepth): Long

    @Query("SELECT * FROM boreholedepth WHERE id=:id")
    fun getById(id: Int): BoreholeDepth

    @Query("SELECT * FROM boreholedepth WHERE boreholeId=:boreholeId ORDER BY updatedAt")
    fun getByBoreholeOrdered(boreholeId: Long): List<BoreholeDepth>

    @Query("SELECT * FROM boreholedepth WHERE boreholeId=:id")
    fun getByBorehole(id: Long): List<BoreholeDepth>

//    Async
    @Query("SELECT * FROM boreholedepth WHERE boreholeId=:id")
    fun getByBoreholeAsync(id: Long): LiveData<List<BoreholeDepth>>

}
