package mishka.mingeo.view.pumping.borehole

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import mishka.mingeo.data.datamanager.DataManager
import mishka.mingeo.data.model.Borehole
import mishka.mingeo.data.model.BoreholeDepth

class BoreholeViewModel(val dataManager: DataManager) : ViewModel() {
    var borehole: Borehole? = null

    fun getDepths(borehole: Borehole): LiveData<List<BoreholeDepth>> {
        return dataManager.getBoreholeDepthsForBorehole(borehole)
    }

    fun onNewDepthValueSet(depth: Int) {
        dataManager.addBoreholeDepth(BoreholeDepth(borehole, depth))
    }

    fun updateDistance(distance: Int) {
        dataManager.setDistanceForBorehole(borehole!!, distance)
    }
}