package mishka.mingeo.view.pumping;

import java.util.List;

import javax.inject.Inject;

import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.Pumping;

public class PumpingPresenter {

    private DataManager dataManager;
    private Pumping pumping;

    @Inject
    public PumpingPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void setPumping(Pumping pumping) {
        this.pumping = pumping;
    }

    private void updateView() {
        dataManager.fetchBoreholesForPumping(pumping, new DataManager.OnItemsFetchedListener<Borehole>() {
            @Override
            public void onItemsFetched(List<Borehole> boreholes) {
//                PumpingPresenter.this.view.updateBoreholes(boreholes);
            }
        });
    }



}
