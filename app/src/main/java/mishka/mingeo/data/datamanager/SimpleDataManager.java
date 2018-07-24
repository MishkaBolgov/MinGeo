package mishka.mingeo.data.datamanager;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import mishka.mingeo.data.datamanager.asyncdboperation.AsyncDbOperationManager;
import mishka.mingeo.data.datamanager.db.BoreholeDao;
import mishka.mingeo.data.datamanager.db.BoreholeDepthDao;
import mishka.mingeo.data.datamanager.db.PumpingDao;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.data.model.Pumping;
import mishka.mingeo.data.datamanager.db.DatabaseHelper;

public class SimpleDataManager implements DataManager {
    private DatabaseHelper dbHelper;
    private PumpingDao pumpingDao;
    private BoreholeDao boreholeDao;
    private BoreholeDepthDao boreholeDepthDao;
    private AsyncDbOperationManager asyncDbOperationManager;

    @Inject
    public SimpleDataManager(DatabaseHelper dbHelper, AsyncDbOperationManager asyncDbOperationManager, PumpingDao pumpingDao, BoreholeDao boreholeDao, BoreholeDepthDao boreholeDepthDao) {
        this.dbHelper = dbHelper;
        this.asyncDbOperationManager = asyncDbOperationManager;
        this.pumpingDao = pumpingDao;
        this.boreholeDao = boreholeDao;
        this.boreholeDepthDao = boreholeDepthDao;
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
    public LiveData<List<Pumping>> getPumpings() {
        return pumpingDao.getAllPumpings();
    }

    @Override
    public void createPumping() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                pumpingDao.addPumping(new Pumping());
                return null;
            }
        }.execute();
    }

    @Override
    public void createBorehole(final Pumping pumping) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                boreholeDao.addBorehole(new Borehole(pumping));
                return null;
            }
        }.execute();
    }


    @Override
    public LiveData<List<Borehole>> getBoreholesForPumping(Pumping pumping) {
        return boreholeDao.getLiveBoreholesForPumping(pumping.getId());
    }

    @Override
    public LiveData<List<BoreholeDepth>> getBoreholeDepthsForBorehole(Borehole borehole) {
        return boreholeDepthDao.getLiveDepthsForBorehole(borehole.id);
    }

    @Override
    public void addBoreholeDepth(final BoreholeDepth boreholeDepth) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                boreholeDepthDao.addBoreholeDepth(boreholeDepth);
                return null;
            }
        }.execute();
    }

    @Override
    public void fetchAllPumpings(OnPumpingFetchedListener listener) {
        new FetchPumpingsAsyncTask(dbHelper, listener).execute();
    }


    @Override
    public void updateBoreholeDepth(BoreholeDepth boreholeDepth, OnDbOperationFinishedListener listener) {
        asyncDbOperationManager.updateBoreholeDepth(boreholeDepth, listener);
    }

    @Override
    public void setPumpPowerForPumping(final Pumping pumping, final float power) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                System.out.println("update power: " + pumping.getPumpPower() + " -> " + power);
                pumpingDao.updatePumpPowerForPumping(pumping.getId(), power);
                return null;
            }
        }.execute();
    }

    @NotNull
    @Override
    public LiveData<Pumping> getPumpingById(int id) {
        return pumpingDao.getLivePumpingById(id);
    }

    @Override
    public void setDistanceForBorehole(@NotNull final Borehole borehole, final int distance) {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                boreholeDao.updateDistance(borehole.getId(), distance);
                return null;
            }
        }.execute();
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
