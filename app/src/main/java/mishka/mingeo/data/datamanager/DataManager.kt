package mishka.mingeo.data.datamanager

import android.arch.lifecycle.LiveData

import mishka.mingeo.data.model.Borehole
import mishka.mingeo.data.model.BoreholeDepth
import mishka.mingeo.data.model.Note
import mishka.mingeo.data.model.Pumping
import org.joda.time.Instant
import java.io.File

interface DataManager {

    val pumpings: LiveData<List<Pumping>>

    fun createPumping(): Long
    fun createBorehole(pumping: Pumping): Long


    fun getBoreholesForPumpingAsync(pumping: Pumping): LiveData<List<Borehole>>
    fun getBoreholesForPumping(pumping: Pumping): List<Borehole>



    fun getBoreholeDepthsForBoreholeAsync(borehole: Borehole): LiveData<List<BoreholeDepth>>
    fun createBoreholeDepth(borehole: Borehole, depth: Float): Long
    fun getPumpingById(id: Long): LiveData<Pumping>

    fun setPumpPowerForPumping(pumping: Pumping, power: Float)
    fun setDistanceForBorehole(borehole: Borehole, distance: Float)
    fun getLiveBoreholeDepths(borehole: Borehole): LiveData<List<BoreholeDepth>>
    fun getBoreholeDepths(borehole: Borehole): List<BoreholeDepth>
    fun getDepthsForPumping(pumping: Pumping): List<List<BoreholeDepth>>

    fun getAllPumpings(): List<Pumping>
    fun getPumping(borehole: Borehole): Pumping


    fun createNote(file: File, pumping: Pumping)
    fun getNotesLive(pumping: Pumping): LiveData<List<Note>>
    fun getLiveBorehole(borehole: Borehole): LiveData<Borehole>
    fun setCentralBorehole(pumping: Pumping, borehole: Borehole)
    fun setInitialDepth(borehole: Borehole, value: Float)
    fun setHeadHeight(borehole: Borehole, value: Float)
    fun setStartPumpingTime(pumping: Pumping, now: Instant)
    fun getStartPumpingTime(borehole: Borehole): Instant?
    fun getStartPumpingTime(pumping: Pumping): Instant?

}
