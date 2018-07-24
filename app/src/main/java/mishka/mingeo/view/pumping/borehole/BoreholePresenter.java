package mishka.mingeo.view.pumping.borehole;

import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.data.model.BoreholeDepth;

public class BoreholePresenter {
    private DataManager dataManager;
    private Borehole borehole;

    @Inject
    public BoreholePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

//    public void setBorehole(Borehole borehole) {
//        this.borehole = borehole;
//        dataManager.fetchBoreholeDepthsForBorehole(borehole, new DataManager.OnItemsFetchedListener<BoreholeDepth>() {
//
//            @Override
//            public void onItemsFetched(List<BoreholeDepth> items) {
////                view.updateBoreholeDepthList(items);
//            }
//        });
//    }

//    private void updateListAndChart(){
//        dataManager.fetchBoreholeDepthsForBorehole(borehole, new DataManager.OnItemsFetchedListener<BoreholeDepth>() {
//            @Override
//            public void onItemsFetched(List<BoreholeDepth> items) {
////                if(items.size() > 0)
////                view.updateChart(items);
////                view.updateBoreholeDepthList(items);
//            }
//        });
//    }

//    public void onBoreholeDepthUpdate(final BoreholeDepth boreholeDepth) {
//        dataManager.updateBoreholeDepth(boreholeDepth, new DataManager.OnDbOperationFinishedListener() {
//            @Override
//            public void onBoreholeDepthUpdated() {
//                dataManager.fetchBoreholeDepthsForBorehole(borehole, new DataManager.OnItemsFetchedListener<BoreholeDepth>() {
//                    @Override
//                    public void onItemsFetched(List<BoreholeDepth> depths) {
////                        view.updateChart(depths);
//                    }
//                });
//            }
//        });
//    }

//    public void onViewResume() {
//        updateListAndChart();
//    }

}
