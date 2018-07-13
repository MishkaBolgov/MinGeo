package mishka.mingeo.data.datamanager;

import android.arch.lifecycle.LiveData;

import java.util.List;

import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.data.model.Pumping;

public interface DataManager{


    void updateBoreholeDepth(BoreholeDepth boreholeDepth, OnDbOperationFinishedListener listener);

    void fetchBoreholesForPumping(Pumping pumping, OnItemsFetchedListener listener);
    void fetchAllPumpings(OnPumpingFetchedListener listener);
    void fetchBoreholeDepthsForBorehole(Borehole borehole, OnItemsFetchedListener listener);

    LiveData<List<Pumping>> getPumpings();


    void createPumping();
    void createBorehole(Pumping pumping);


    LiveData<List<Borehole>> getBoreholesForPumping(Pumping pumping);
    LiveData<List<BoreholeDepth>> getBoreholeDepthsForBorehole(Borehole borehole);
    void addBoreholeDepth(BoreholeDepth boreholeDepth);


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
