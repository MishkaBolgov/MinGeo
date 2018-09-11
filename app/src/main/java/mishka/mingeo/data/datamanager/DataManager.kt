package mishka.mingeo.data.datamanager

import android.arch.lifecycle.LiveData

import mishka.mingeo.data.model.Borehole
import mishka.mingeo.data.model.BoreholeDepth
import mishka.mingeo.data.model.Note
import mishka.mingeo.data.model.Pumping
import java.io.File

interface DataManager {

    val pumpings: LiveData<List<Pumping>>

    fun createPumping(): Long
    fun createBorehole(pumping: Pumping): Borehole


    fun getLiveBoreholesForPumping(pumping: Pumping): LiveData<List<Borehole>>
    fun getBoreholesForPumping(pumping: Pumping): List<Borehole>



    fun getBoreholeDepthsForBorehole(borehole: Borehole): LiveData<List<BoreholeDepth>>
    fun createBoreholeDepth(borehole: Borehole, depth: Float)
    fun getPumpingById(id: Int): LiveData<Pumping>

    fun setPumpPowerForPumping(pumping: Pumping, power: Float)
    fun setDistanceForBorehole(borehole: Borehole, distance: Int)
    fun getLiveBoreholeDepths(borehole: Borehole): LiveData<List<BoreholeDepth>>
    fun getBoreholeDepths(borehole: Borehole): List<BoreholeDepth>
    fun getDepthsForPumping(pumping: Pumping): List<List<BoreholeDepth>>

    fun getAllPumpings(): List<Pumping>

    fun createNote(file: File, pumping: Pumping)
    fun getNotesLive(pumping: Pumping): LiveData<List<Note>>

}
