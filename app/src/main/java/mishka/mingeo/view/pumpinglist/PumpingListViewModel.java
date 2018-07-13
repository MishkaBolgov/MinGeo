package mishka.mingeo.view.pumpinglist;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.data.model.Pumping;

public class PumpingListViewModel extends ViewModel {
    private DataManager dataManager;
    private LiveData<List<Pumping>> pumpings;

    public PumpingListViewModel(DataManager dataManager) {
        this.dataManager = dataManager;
        pumpings = dataManager.getPumpings();
    }

    public LiveData<List<Pumping>> getPumpings(){
        return pumpings;
    }

    public void onAddPumpingClick() {
        dataManager.createPumping();
    }
}
