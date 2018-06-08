package mishka.mingeo.data.datamanager.asyncdboperation;

import android.os.AsyncTask;

import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.data.datamanager.db.DatabaseHelper;
import mishka.mingeo.data.model.BoreholeDepth;

class AsyncBoreholeDepthUpdate extends AsyncTask<Void, Void, Void>{
    private DatabaseHelper databaseHelper;
    private BoreholeDepth updatedBoreholeDepth;
    private DataManager.OnDbOperationFinishedListener listener;
    public AsyncBoreholeDepthUpdate(DatabaseHelper databaseHelper, BoreholeDepth boreholeDepth, DataManager.OnDbOperationFinishedListener listener) {
        this.databaseHelper = databaseHelper;
        this.updatedBoreholeDepth = boreholeDepth;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        databaseHelper.updateBoreholeDepth(updatedBoreholeDepth);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onBoreholeDepthUpdated();
    }
}
