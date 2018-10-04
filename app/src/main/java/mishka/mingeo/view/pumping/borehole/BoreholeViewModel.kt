package mishka.mingeo.view.pumping.borehole

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import mishka.mingeo.data.datamanager.DataManager
import mishka.mingeo.data.model.Borehole
import org.joda.time.Instant
import javax.inject.Inject

class BoreholeViewModel(val dataManager: DataManager, val borehole: Borehole) : ViewModel() {
    val startPumpingTime: Instant? = dataManager.getStartPumpingTime(borehole)

    fun getDepths() = dataManager.getLiveBoreholeDepths(borehole)
    fun getBorehole() = dataManager.getLiveBorehole(borehole)

    fun addDepth(depth: Float) {
        dataManager.createBoreholeDepth(borehole, depth)
    }

    fun updateDistance(distance: Float) {
        dataManager.setDistanceForBorehole(borehole, distance)
    }

    fun setInitialDepth(value: Float) {
        dataManager.setInitialDepth(borehole, value)
    }

    fun setHeadHeight(value: Float) {
        dataManager.setHeadHeight(borehole, value)
    }

    fun isDepthAddingAvailable() = (startPumpingTime != null)

    class Factory @Inject constructor(val dataManager: DataManager, val borehole: Borehole) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return BoreholeViewModel(dataManager, borehole) as T
        }
    }
}