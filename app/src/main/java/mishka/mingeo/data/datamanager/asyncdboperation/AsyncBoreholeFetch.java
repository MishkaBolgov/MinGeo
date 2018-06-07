package mishka.mingeo.data.datamanager.asyncdboperation;

import android.os.AsyncTask;

import java.util.List;

import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.data.datamanager.db.DatabaseHelper;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.DatabaseEntity;
import mishka.mingeo.data.model.Pumping;

class AsyncBoreholeFetch extends AsyncTask<Void, Void, List<Borehole>> {
    private DatabaseHelper databaseHelper;
    private Pumping pumping;
    private DataManager.OnItemsFetchedListener listener;

    public AsyncBoreholeFetch(DatabaseHelper databaseHelper, Pumping pumping, DataManager.OnItemsFetchedListener listener) {
        this.databaseHelper = databaseHelper;
        this.pumping = pumping;
        this.listener = listener;
    }

    @Override
    protected List<Borehole> doInBackground(Void... voids) {
        return databaseHelper.getBoreholesForPumping(pumping);
    }

    @Override
    protected void onPostExecute(List<Borehole> boreholes) {
        super.onPostExecute(boreholes);
        listener.onBoreholesFetched(boreholes);
    }
}
