package mishka.mingeo.data.datamanager.asyncdboperation;

import android.os.AsyncTask;

import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.data.datamanager.db.DatabaseHelper;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.data.model.DatabaseEntity;
import mishka.mingeo.data.model.Pumping;

class AsyncAdd extends AsyncTask<Void, Void, DatabaseEntity> {
    private DatabaseHelper databaseHelper;
    private DatabaseEntity objectToAdd;
    private DataManager.OnItemAddedListener listener;

    public AsyncAdd(DatabaseHelper databaseHelper, DatabaseEntity objectToAdd, DataManager.OnItemAddedListener listener) {
        this.databaseHelper = databaseHelper;
        this.objectToAdd = objectToAdd;
        this.listener = listener;
    }

    private Class getWorkClass(){
        return objectToAdd.getClass();
    }

    @Override
    protected DatabaseEntity doInBackground(Void... v) {
        Class objectClass = getWorkClass();
        int id = -1;

        if (objectClass == Pumping.class) {
            id = databaseHelper.addPumping((Pumping) objectToAdd);
            return databaseHelper.getPumpingById(id);
        } else if (objectClass == Borehole.class) {
            id = databaseHelper.addBorehole((Borehole) objectToAdd);
            return databaseHelper.getBoreholeById(id);
        } else if(objectClass == BoreholeDepth.class){
            id = databaseHelper.addBoreholeDepth((BoreholeDepth) objectToAdd);
            return databaseHelper.getBoreholeDepthById(id);
        }
        return null;
    }

    @Override
    protected void onPostExecute(DatabaseEntity addedObject) {
        super.onPostExecute(addedObject);
        listener.onItemCreated(addedObject);
    }



}
