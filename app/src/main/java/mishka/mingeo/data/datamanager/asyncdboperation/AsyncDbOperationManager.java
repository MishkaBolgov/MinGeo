package mishka.mingeo.data.datamanager.asyncdboperation;

import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.data.model.DatabaseEntity;
import mishka.mingeo.data.model.Pumping;

public interface AsyncDbOperationManager {
    void add(DatabaseEntity object, DataManager.OnItemAddedListener listener);
    void fetchBoreholeForPumping(DataManager.OnItemsFetchedListener listener, Pumping pumping);

    void updateBoreholeDepth(BoreholeDepth boreholeDepth);
}