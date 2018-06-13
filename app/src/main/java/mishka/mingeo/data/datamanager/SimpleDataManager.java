package mishka.mingeo.data.datamanager;

import android.os.AsyncTask;

import java.util.List;

import javax.inject.Inject;

import mishka.mingeo.data.datamanager.asyncdboperation.AsyncDbOperationManager;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.data.model.Pumping;
import mishka.mingeo.data.datamanager.db.DatabaseHelper;

public class SimpleDataManager implements DataManager {
    private DatabaseHelper dbHelper;
    private AsyncDbOperationManager asyncDbOperationManager;

    @Inject
    public SimpleDataManager(DatabaseHelper dbHelper, AsyncDbOperationManager asyncDbOperationManager) {
        this.dbHelper = dbHelper;
        this.asyncDbOperationManager = asyncDbOperationManager;
    }


    @Override
    public void createPumping(OnItemAddedListener listener) {
        asyncDbOperationManager.add(new Pumping(), listener);
    }

    @Override
    public void createBorehole(Pumping pumping, OnItemAddedListener listener) {
        asyncDbOperationManager.add(new Borehole(pumping), listener);
    }


    @Override
    public void createBoreholeDepth(Borehole borehole, int depth, OnItemAddedListener listener) {
        asyncDbOperationManager.add(new BoreholeDepth(borehole, depth), listener);
    }

    @Override
    public void fetchBoreholesForPumping(Pumping pumping, OnItemsFetchedListener listener) {
        asyncDbOperationManager.fetchBoreholesForPumping(pumping, listener);
    }

    @Override
    public void fetchBoreholeDepthsForBorehole(Borehole borehole, OnItemsFetchedListener listener) {
        asyncDbOperationManager.fetchDepthsForBorehole(borehole, listener);
    }

    @Override
    public void fetchAllPumpings(OnPumpingFetchedListener listener) {
        new FetchPumpingsAsyncTask(dbHelper, listener).execute();
    }


    @Override
    public void updateBoreholeDepth(BoreholeDepth boreholeDepth, OnDbOperationFinishedListener listener) {
        asyncDbOperationManager.updateBoreholeDepth(boreholeDepth, listener);
    }

    private static class FetchPumpingsAsyncTask extends AsyncTask<Void, Void, List<Pumping>> {

        private DatabaseHelper databaseHelper;
        private OnPumpingFetchedListener listener;

        public FetchPumpingsAsyncTask(DatabaseHelper databaseHelper, OnPumpingFetchedListener listener) {
            this.databaseHelper = databaseHelper;
            this.listener = listener;
        }


        @Override
        protected List<Pumping> doInBackground(Void... voids) {
            return databaseHelper.getAllPumpings();
        }

        @Override
        protected void onPostExecute(List<Pumping> pumpings) {
            super.onPostExecute(pumpings);
            listener.onAllPumpingsFetched(pumpings);
        }
    }

}
