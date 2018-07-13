package mishka.mingeo.view.pumping;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.Pumping;

public class PumpingViewModel extends ViewModel {
    private DataManager dataManager;
    private LiveData<List<Borehole>> boreholes;
    private Pumping pumping;

    public PumpingViewModel(DataManager dataManager, Pumping pumping) {
        this.dataManager = dataManager;
        boreholes = dataManager.getBoreholesForPumping(pumping);
        this.pumping = pumping;
    }

    public LiveData<List<Borehole>> getBoreholes() {
        return boreholes;
    }

    public void onCreateBoreholeClick(){
        dataManager.createBorehole(pumping);
    }
}
