package mishka.mingeo.data.datamanager.db;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.data.model.Pumping;

public class SimpleDatabaseHelper implements DatabaseHelper {

    private AppDatabase database;

    @Inject
    public SimpleDatabaseHelper(AppDatabase database) {
        this.database = database;
    }

    @Override
    public int addPumping(Pumping pumping) {
        return (int) database.pumpingDao().insertPumping(pumping);
    }

    @Override
    public int addBorehole(Borehole borehole) {
        return (int) database.boreholeDao().createBorehole(borehole);
    }

    @Override
    public int addBoreholeDepth(BoreholeDepth boreholeDepth) {
        return (int) database.boreholeDepthDao().addBoreholeDepth(boreholeDepth);
    }

    @Override
    public void updateBoreholeDepth(BoreholeDepth updatedBoreholeDepth) {
        database.boreholeDepthDao().updateBoreholeDepth(updatedBoreholeDepth);
    }

    @Override
    public LiveData<List<BoreholeDepth>> getBoreholeDepthsForBorehole(Borehole borehole) {
        return database.boreholeDepthDao().getLiveDepthsForBorehole(borehole.getId());
    }


    @Override
    public Pumping getPumpingById(int id) {
        return database.pumpingDao().getPumpingById(id);
    }

    @Override
    public Borehole getBoreholeById(int id) {
        return database.boreholeDao().getBoreholeById(id);
    }

    @Override
    public BoreholeDepth getBoreholeDepthById(int id) {
        return database.boreholeDepthDao().getBoreholeDepthById(id);
    }


    @Override
    public List<Borehole> getBoreholesForPumping(Pumping pumping) {
        return database.boreholeDao().getBoreholesForPumping(pumping.getId());
    }

    @Override
    public List<Pumping> getAllPumpings() {
        return database.pumpingDao().getAll();
    }


}
