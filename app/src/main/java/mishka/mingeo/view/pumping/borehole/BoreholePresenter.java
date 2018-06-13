package mishka.mingeo.view.pumping.borehole;

import com.google.gson.Gson;

import java.util.List;

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
        dataManager.fetchBoreholeDepthsForBorehole(borehole, new DataManager.OnItemsFetchedListener<BoreholeDepth>() {

            @Override
            public void onItemsFetched(List<BoreholeDepth> items) {
                view.updateBoreholeDepthList(items);
            }
        });
    }

    @Override
    public void createBoreholeDepth(int depth) {
        dataManager.createBoreholeDepth(borehole, depth, new DataManager.OnItemAddedListener<BoreholeDepth>() {
            @Override
            public void onItemCreated(BoreholeDepth addedItem) {
                updateListAndChart();
            }
        });
    }

    private void updateListAndChart(){
        dataManager.fetchBoreholeDepthsForBorehole(borehole, new DataManager.OnItemsFetchedListener<BoreholeDepth>() {
            @Override
            public void onItemsFetched(List<BoreholeDepth> items) {
                System.out.println("depths: "+new Gson().toJson(items));
                if(items.size() > 0)
                view.updateChart(items);
                view.updateBoreholeDepthList(items);
            }
        });
    }

    @Override
    public void onBoreholeDepthUpdate(final BoreholeDepth boreholeDepth) {
        dataManager.updateBoreholeDepth(boreholeDepth, new DataManager.OnDbOperationFinishedListener() {
            @Override
            public void onBoreholeDepthUpdated() {
                dataManager.fetchBoreholeDepthsForBorehole(borehole, new DataManager.OnItemsFetchedListener<BoreholeDepth>() {
                    @Override
                    public void onItemsFetched(List<BoreholeDepth> depths) {
                        view.updateChart(depths);
                    }
                });
            }
        });
    }

    @Override
    public void onViewResume() {
        updateListAndChart();
    }

}
