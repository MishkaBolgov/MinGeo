package mishka.mingeo.view.pumping.borehole;

import javax.inject.Inject;

import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.view.MvpView;

public class BoreholePresenter implements BoreholeMvpPresenter {
    private DataManager dataManager;
    private BoreholeMvpView view;
    private Borehole borehole;

    @Inject
    public BoreholePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void setMvpView(MvpView view) {
        this.view = (BoreholeMvpView) view;
    }

    @Override
    public void setBorehole(Borehole borehole) {
        this.borehole = borehole;
    }

    @Override
    public void addBoreholeDepth() {
        dataManager.createBoreholeDepth(borehole, new DataManager.OnItemAddedListener<BoreholeDepth>(){
            @Override
            public void onItemCreated(BoreholeDepth boreholeDepth) {
                view.addBoreholeDepth(boreholeDepth);
            }
        });
    }

    @Override
    public void onBoreholeDepthUpdate(BoreholeDepth boreholeDepth) {
        dataManager.updateBoreholeDepth(boreholeDepth);
    }

}
