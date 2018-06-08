package mishka.mingeo.data.datamanager.asyncdboperation;

import android.os.AsyncTask;

import java.util.List;

import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.data.datamanager.db.DatabaseHelper;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.data.model.DatabaseEntity;
import mishka.mingeo.data.model.Pumping;

class AsyncFetch extends AsyncTask<Void, Void, List<? extends DatabaseEntity>> {
    private DatabaseHelper databaseHelper;
    private DatabaseEntity ownerEntity;
    private DataManager.OnItemsFetchedListener listener;
    private Class fetchClass;

    public AsyncFetch(DatabaseHelper databaseHelper, DatabaseEntity ownerEntity, Class<? extends DatabaseEntity> fetchClass, DataManager.OnItemsFetchedListener listener) {
        this.databaseHelper = databaseHelper;
        this.ownerEntity = ownerEntity;
        this.listener = listener;
        this.fetchClass = fetchClass;
    }

    @Override
    protected List<? extends DatabaseEntity> doInBackground(Void... voids) {
        if (fetchClass == Borehole.class)
            return databaseHelper.getBoreholesForPumping((Pumping) ownerEntity);
        else if (fetchClass == BoreholeDepth.class)
            return databaseHelper.getBoreholeDepthsForBorehole((Borehole) ownerEntity);
        else return null;
    }

    @Override
    protected void onPostExecute(List<? extends DatabaseEntity> databaseEntities) {
        super.onPostExecute(databaseEntities);
        listener.onItemsFetched(databaseEntities);
    }
}
