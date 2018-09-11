package mishka.mingeo.view.pumping.note

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import mishka.mingeo.data.datamanager.DataManager
import mishka.mingeo.data.model.Pumping
import javax.inject.Inject

class NotesViewModel(val dataManager: DataManager, pumping: Pumping) : ViewModel() {
    val notes = dataManager.getNotesLive(pumping)

    class Factory @Inject constructor(val dataManager: DataManager, val pumping: Pumping) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NotesViewModel(dataManager, pumping) as T
        }
    }
}