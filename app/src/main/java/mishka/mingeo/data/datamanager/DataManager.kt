package mishka.mingeo.data.datamanager

import android.arch.lifecycle.LiveData

import mishka.mingeo.data.model.Borehole
import mishka.mingeo.data.model.BoreholeDepth
import mishka.mingeo.data.model.Pumping

interface DataManager {

    val pumpings: LiveData<List<Pumping>>


    fun updateBoreholeDepth(boreholeDepth: BoreholeDepth, listener: OnDbOperationFinishedListener)

    fun fetchBoreholesForPumping(pumping: Pumping, listener: OnItemsFetchedListener<*>)
    fun fetchAllPumpings(listener: OnPumpingFetchedListener)
    fun fetchBoreholeDepthsForBorehole(borehole: Borehole, listener: OnItemsFetchedListener<*>)


    fun createPumping()
    fun createBorehole(pumping: Pumping)


    fun getBoreholesForPumping(pumping: Pumping): LiveData<List<Borehole>>
    fun getBoreholeDepthsForBorehole(borehole: Borehole): LiveData<List<BoreholeDepth>>
    fun addBoreholeDepth(boreholeDepth: BoreholeDepth)
    fun getPumpingById(id: Int): LiveData<Pumping>

    fun setPumpPowerForPumping(pumping: Pumping, power: Float)
    fun setDistanceForBorehole(borehole: Borehole, distance: Int)

    interface OnPumpingFetchedListener {
        fun onAllPumpingsFetched(pumpings: List<Pumping>)
    }

    interface OnItemsFetchedListener<T> {
        fun onItemsFetched(items: List<T>)
    }

    interface OnItemAddedListener<T> {
        fun onItemCreated(addedItem: T)
    }

    interface OnDbOperationFinishedListener {
        fun onBoreholeDepthUpdated()
    }

}
