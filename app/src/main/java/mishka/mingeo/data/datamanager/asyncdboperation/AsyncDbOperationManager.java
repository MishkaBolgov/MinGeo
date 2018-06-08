package mishka.mingeo.data.datamanager.asyncdboperation;

import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.data.model.DatabaseEntity;
import mishka.mingeo.data.model.Pumping;

public interface AsyncDbOperationManager {
    void add(DatabaseEntity object, DataManager.OnItemAddedListener listener);
    void fetchBoreholesForPumping(Pumping pumping, DataManager.OnItemsFetchedListener listener);

    void updateBoreholeDepth(BoreholeDepth boreholeDepth, DataManager.OnDbOperationFinishedListener listener);

    void fetchDepthsForBorehole(Borehole borehole, DataManager.OnItemsFetchedListener listener);
}
