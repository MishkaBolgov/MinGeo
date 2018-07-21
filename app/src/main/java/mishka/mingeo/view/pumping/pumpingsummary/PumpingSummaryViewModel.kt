package mishka.mingeo.view.pumping.pumpingsummary

import android.arch.lifecycle.LiveData
import mishka.mingeo.data.datamanager.DataManager
import mishka.mingeo.data.model.Borehole
import mishka.mingeo.data.model.Pumping
import javax.inject.Inject

class PumpingSummaryViewModel @Inject constructor(dataManager: DataManager, pumping: Pumping) {
    val boreholes: LiveData<List<Borehole>> = dataManager.getBoreholesForPumping(pumping)
}