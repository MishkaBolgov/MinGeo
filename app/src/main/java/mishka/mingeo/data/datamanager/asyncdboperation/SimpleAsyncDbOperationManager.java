package mishka.mingeo.data.datamanager.asyncdboperation;

import javax.inject.Inject;

import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.data.datamanager.db.DatabaseHelper;
import mishka.mingeo.data.model.Borehole;
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
    public void fetchBoreholesForPumping(Pumping pumping, DataManager.OnItemsFetchedListener listener) {
        new AsyncFetch(databaseHelper, pumping, Borehole.class, listener).execute();
    }

    @Override
    public void updateBoreholeDepth(BoreholeDepth boreholeDepth, DataManager.OnDbOperationFinishedListener listener) {
        new AsyncBoreholeDepthUpdate(databaseHelper, boreholeDepth, listener).execute();
    }

    @Override
    public void fetchDepthsForBorehole(Borehole borehole, DataManager.OnItemsFetchedListener listener) {
        new AsyncFetch(databaseHelper, borehole, BoreholeDepth.class, listener).execute();
    }
}
