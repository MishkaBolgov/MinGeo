package mishka.mingeo.data.datamanager;

import android.os.AsyncTask;

import java.util.List;

import javax.inject.Inject;

import mishka.mingeo.data.Pumping;
import mishka.mingeo.data.datamanager.db.DatabaseHelper;

public class SimpleDataManager implements DataManager {
    private DatabaseHelper dbHelper;

    @Inject
    public SimpleDataManager(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public void addPumping(Pumping pumping, OnPumpingAddedListener listener) {
        new AddPumpingAsyncTask(dbHelper, pumping, listener).execute();
    }

    @Override
    public void getAllPumpings(OnPumpingFetchedListener listener) {
        new FetchPumpingsAsyncTask(dbHelper, listener).execute();
    }

    private static class AddPumpingAsyncTask extends AsyncTask<Void, Void, Void> {

        private DatabaseHelper databaseHelper;
        private Pumping pumping;
        private OnPumpingAddedListener listener;

        public AddPumpingAsyncTask(DatabaseHelper databaseHelper, Pumping pumping, OnPumpingAddedListener listener) {
            this.databaseHelper = databaseHelper;
            this.pumping = pumping;
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            databaseHelper.addPumping(pumping);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listener.pumpingAdded();
        }
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
