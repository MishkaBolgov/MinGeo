package mishka.mingeo.view.pumping

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import javax.inject.Inject

import mishka.mingeo.data.datamanager.DataManager
import mishka.mingeo.data.model.Borehole
import mishka.mingeo.data.model.BoreholeDepth
import mishka.mingeo.data.model.Pumping

class PumpingViewModel(val dataManager: DataManager, private val pumping: Pumping) : ViewModel() {
    val boreholes: LiveData<List<Borehole>> = dataManager.getLiveBoreholesForPumping(pumping)

    fun onCreateBoreholeClick() = dataManager.createBorehole(pumping)

    fun setPumpPower(value: Float) = dataManager.setPumpPowerForPumping(pumping, value)

    fun getPumping() = dataManager.getPumpingById(pumping.id)

    fun getSummaryDepth(): List<List<BoreholeDepth>> = dataManager.getDepthsForPumping(pumping)


    class Factory @Inject constructor(private val dataManager: DataManager, private val pumping: Pumping) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PumpingViewModel(dataManager, pumping) as T
        }
    }

}
