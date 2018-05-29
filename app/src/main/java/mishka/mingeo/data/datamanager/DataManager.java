package mishka.mingeo.data.datamanager;

import java.util.List;

import mishka.mingeo.data.Pumping;
import mishka.mingeo.data.datamanager.db.DatabaseHelper;

public interface DataManager{

    void addPumping(Pumping pumping, OnPumpingAddedListener listener);

    void getAllPumpings(OnPumpingFetchedListener listener);

    interface OnPumpingAddedListener{
        void pumpingAdded();
    }

    interface OnPumpingFetchedListener{
         void onAllPumpingsFetched(List<Pumping> pumpings);
    }
}
