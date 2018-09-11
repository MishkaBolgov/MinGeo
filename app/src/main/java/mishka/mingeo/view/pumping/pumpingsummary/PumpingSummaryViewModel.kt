package mishka.mingeo.view.pumping.pumpingsummary

import android.arch.lifecycle.LiveData
import mishka.mingeo.data.datamanager.DataManager
import mishka.mingeo.data.model.Borehole
import mishka.mingeo.data.model.BoreholeDepth
import mishka.mingeo.data.model.Pumping
import javax.inject.Inject

class PumpingSummaryViewModel @Inject constructor(val dataManager: DataManager, val pumping: Pumping) {

    val boreholes: LiveData<List<Borehole>> = dataManager.getLiveBoreholesForPumping(pumping)


    fun updatePumpPower(power: Float) {
        dataManager.setPumpPowerForPumping(pumping, power)
    }

    fun getPumping(): LiveData<Pumping> = dataManager.getPumpingById(pumping.id)
    fun getDepths(borehole: Borehole):LiveData<List<BoreholeDepth>>  = dataManager.getBoreholeDepthsForBorehole(borehole)

}