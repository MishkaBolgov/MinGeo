package mishka.mingeo.data.datamanager

import android.arch.lifecycle.LiveData

import java.io.File
import java.util.ArrayList

import javax.inject.Inject

import mishka.mingeo.data.datamanager.db.BoreholeDao
import mishka.mingeo.data.datamanager.db.BoreholeDepthDao
import mishka.mingeo.data.datamanager.db.NoteDao
import mishka.mingeo.data.datamanager.db.PumpingDao
import mishka.mingeo.data.model.Borehole
import mishka.mingeo.data.model.BoreholeDepth
import mishka.mingeo.data.model.Note
import mishka.mingeo.data.model.Pumping
import org.joda.time.Instant

class SimpleDataManager @Inject constructor(private val pumpingDao: PumpingDao,
                                            private val boreholeDao: BoreholeDao,
                                            private val boreholeDepthDao: BoreholeDepthDao,
                                            private val noteDao: NoteDao) : DataManager {

    override val pumpings: LiveData<List<Pumping>>
        get() = pumpingDao.getAllAsync()

    //    Pumping
    override fun createPumping() = pumpingDao.insert(Pumping())
    override fun setPumpPowerForPumping(pumping: Pumping, power: Float) = pumpingDao.updatePumpPower(pumping.id, power)
    override fun setStartPumpingTime(pumping: Pumping, time: Instant) = pumpingDao.updateStartPumpingTime(pumping.id, time.millis)
    override fun getPumping(borehole: Borehole) = pumpingDao.getById(borehole.pumpingId)


    //    Borehole
    override fun createBorehole(pumping: Pumping) = boreholeDao.insertBorehole(Borehole(pumping))
    override fun getBoreholesForPumpingAsync(pumping: Pumping) = boreholeDao.getByPumpingAsync(pumping.id)


    //    BoreholeDepth
    override fun createBoreholeDepth(borehole: Borehole, depth: Float) = boreholeDepthDao.insert(BoreholeDepth(borehole, depth))
    override fun getBoreholeDepthsForBoreholeAsync(borehole: Borehole) = boreholeDepthDao.getByBoreholeAsync(borehole.id)



    override fun getPumpingById(id: Long): LiveData<Pumping> {
        return pumpingDao.getByIdAsync(id)
    }

    override fun setDistanceForBorehole(borehole: Borehole, distance: Float) {
        boreholeDao.updateDistance(borehole.id, distance)
    }

    override fun getLiveBoreholeDepths(borehole: Borehole): LiveData<List<BoreholeDepth>> {
        return boreholeDepthDao.getByBoreholeAsync(borehole.id)
    }

    override fun getBoreholesForPumping(pumping: Pumping): List<Borehole> {
        return boreholeDao.getByPumping(pumping.id)
    }

    override fun getBoreholeDepths(borehole: Borehole): List<BoreholeDepth> {
        return boreholeDepthDao.getByBoreholeOrdered(borehole.id)
    }

    override fun getDepthsForPumping(pumping: Pumping): List<List<BoreholeDepth>> {
        val all = ArrayList<List<BoreholeDepth>>()

        val boreholes = boreholeDao.getByPumping(pumping.id)
        for (borehole in boreholes) {
            all.add(boreholeDepthDao.getByBorehole(borehole.id))
        }

        return all
    }

    override fun getAllPumpings(): List<Pumping> = pumpingDao.getAll()



    override fun createNote(file: File, pumping: Pumping) {
        noteDao.insert(Note(file.path, pumping))
    }

    override fun getNotesLive(pumping: Pumping): LiveData<List<Note>> {
        return noteDao.getByPumpingAsync(pumping.id)
    }

    override fun getLiveBorehole(borehole: Borehole): LiveData<Borehole> {
        return boreholeDao.getByIdAsync(borehole.id)
    }

    override fun setCentralBorehole(pumping: Pumping, borehole: Borehole) {
        pumpingDao.updateCentralBorehole(borehole.id, pumping.id)
    }

    override fun setInitialDepth(borehole: Borehole, value: Float) {
        boreholeDao.updateInitialDepth(borehole.id, value)
    }

    override fun setHeadHeight(borehole: Borehole, value: Float) {
        boreholeDao.updateHeadHeight(borehole.id, value)
    }

    override fun getStartPumpingTime(borehole: Borehole) = pumpingDao.getById(borehole.pumpingId).startPumpingTime
    override fun getStartPumpingTime(pumping: Pumping) = pumpingDao.getById(pumping.id).startPumpingTime


}
