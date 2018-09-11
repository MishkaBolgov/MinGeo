package mishka.mingeo.view.pumping.borehole

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import mishka.mingeo.data.datamanager.DataManager
import mishka.mingeo.data.model.Borehole
import javax.inject.Inject

class BoreholeViewModel(val dataManager: DataManager, val borehole: Borehole) : ViewModel() {
    fun getDepths() = dataManager.getLiveBoreholeDepths(borehole)

    fun addDepth(depth: Float) {
        dataManager.createBoreholeDepth(borehole, depth)
    }

    class Factory @Inject constructor(val dataManager: DataManager, val borehole: Borehole) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return BoreholeViewModel(dataManager, borehole) as T
        }
    }
}