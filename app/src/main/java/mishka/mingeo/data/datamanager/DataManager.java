package mishka.mingeo.data.datamanager;

import java.util.List;

import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.data.model.Pumping;

public interface DataManager{

    void createPumping(OnItemAddedListener listener);
    void createBorehole(Pumping pumping, OnItemAddedListener listener);
    void createBoreholeDepth(Borehole borehole, OnItemAddedListener listener);

    void fetchBoreholesForPumping(Pumping pumping, OnItemsFetchedListener listener);


    void getAllPumpings(OnPumpingFetchedListener listener);

    void updateBoreholeDepth(BoreholeDepth boreholeDepth);

    interface OnPumpingFetchedListener{
         void onAllPumpingsFetched(List<Pumping> pumpings);
    }

    interface OnItemsFetchedListener<T>{
         void onBoreholesFetched(List<T> boreholes);
    }

    interface OnItemAddedListener<T>{
        void onItemCreated(T addedObject);
    }

}
