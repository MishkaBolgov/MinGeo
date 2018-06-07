package mishka.mingeo.data.datamanager.asyncdboperation;

import javax.inject.Inject;

import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.data.datamanager.db.DatabaseHelper;
import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.data.model.DatabaseEntity;
import mishka.mingeo.data.model.Pumping;

public class SimpleAsyncDbOperationManager implements AsyncDbOperationManager {

    private DatabaseHelper databaseHelper;

    @Inject
    public SimpleAsyncDbOperationManager(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public void add(DatabaseEntity object, DataManager.OnItemAddedListener listener) {
        new AsyncAdd(databaseHelper, object, listener).execute();
    }

    @Override
    public void fetchBoreholeForPumping(DataManager.OnItemsFetchedListener listener, Pumping pumping) {
        new AsyncBoreholeFetch(databaseHelper, pumping, listener).execute();
    }

    @Override
    public void updateBoreholeDepth(BoreholeDepth boreholeDepth) {
        new AsyncBoreholeDepthUpdate(databaseHelper, boreholeDepth).execute();
    }
}
