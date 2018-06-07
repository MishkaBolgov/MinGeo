package mishka.mingeo.data.datamanager.asyncdboperation;

import android.os.AsyncTask;

import mishka.mingeo.data.datamanager.db.DatabaseHelper;
import mishka.mingeo.data.model.BoreholeDepth;

class AsyncBoreholeDepthUpdate extends AsyncTask<Void, Void, Void>{
    private DatabaseHelper databaseHelper;
    private BoreholeDepth updatedBoreholeDepth;
    public AsyncBoreholeDepthUpdate(DatabaseHelper databaseHelper, BoreholeDepth boreholeDepth) {
        this.databaseHelper = databaseHelper;
        this.updatedBoreholeDepth = boreholeDepth;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        databaseHelper.updateBoreholeDepth(updatedBoreholeDepth);
        return null;
    }
}
