package mishka.mingeo.view.pumpinglist


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import mishka.mingeo.data.datamanager.DataManager
import mishka.mingeo.data.model.Pumping
import javax.inject.Inject

class PumpingListViewModel(private val dataManager: DataManager) : ViewModel() {
    val pumpings: LiveData<List<Pumping>> = dataManager.pumpings

    fun onCreatePumpingClick() = dataManager.createPumping()


    class Factory @Inject constructor(val dataManager: DataManager) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PumpingListViewModel(dataManager) as T
        }
    }
}
