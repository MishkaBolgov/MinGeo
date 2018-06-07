package mishka.mingeo.view.pumping;

import java.util.List;

import javax.inject.Inject;

import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.Pumping;
import mishka.mingeo.view.MvpView;

public class PumpingPresenter implements PumpingMvpPresenter {

    private PumpingMvpView view;
    private DataManager dataManager;
    private Pumping pumping;

    @Inject
    public PumpingPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void setPumping(Pumping pumping) {
        this.pumping = pumping;

        dataManager.createBorehole(pumping, new DataManager.OnItemAddedListener<Borehole>() {
            @Override
            public void onItemCreated(Borehole addedObject) {

            }
        });
        dataManager.createBorehole(pumping, new DataManager.OnItemAddedListener<Borehole>() {
            @Override
            public void onItemCreated(Borehole addedObject) {

            }
        });
    }

    @Override
    public void setMvpView(MvpView view) {
        this.view = (PumpingMvpView) view;
        dataManager.fetchBoreholesForPumping(pumping, new DataManager.OnItemsFetchedListener<Borehole>() {
            @Override
            public void onBoreholesFetched(List<Borehole> boreholes) {
                PumpingPresenter.this.view.updateBoreholes(boreholes);
            }
        });

    }

    @Override
    public void onAddBoreholeClick() {
    }
}
