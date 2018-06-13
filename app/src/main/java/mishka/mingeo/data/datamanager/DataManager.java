package mishka.mingeo.data.datamanager;

import java.util.List;

import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.data.model.Pumping;

public interface DataManager{

    void createPumping(OnItemAddedListener listener);
    void createBorehole(Pumping pumping, OnItemAddedListener listener);
    void createBoreholeDepth(Borehole borehole, int depth, OnItemAddedListener listener);

    void updateBoreholeDepth(BoreholeDepth boreholeDepth, OnDbOperationFinishedListener listener);

    void fetchBoreholesForPumping(Pumping pumping, OnItemsFetchedListener listener);
    void fetchAllPumpings(OnPumpingFetchedListener listener);
    void fetchBoreholeDepthsForBorehole(Borehole borehole, OnItemsFetchedListener listener);


    interface OnPumpingFetchedListener{
         void onAllPumpingsFetched(List<Pumping> pumpings);
    }

    interface OnItemsFetchedListener<T>{
         void onItemsFetched(List<T> items);
    }

    interface OnItemAddedListener<T>{
        void onItemCreated(T addedItem);
    }

    interface OnDbOperationFinishedListener {
        void onBoreholeDepthUpdated();
    }

}
