package mishka.mingeo.data.datamanager.db;

import com.google.gson.Gson;

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
        return (int) database.pumpingDao().addPumping(pumping);
    }

    @Override
    public int addBorehole(Borehole borehole) {
        return (int) database.boreholeDao().addBorehole(borehole);
    }

    @Override
    public int addBoreholeDepth(BoreholeDepth boreholeDepth) {
        return (int) database.boreholeDepthDao().addBoreholeDepth(boreholeDepth);
    }

    @Override
    public void updateBoreholeDepth(BoreholeDepth updatedBoreholeDepth) {
        System.out.println("here");
        for (BoreholeDepth boreholeDepth : database.boreholeDepthDao().getAll())
            System.out.println("{b} borehole depth #" + boreholeDepth.getId() + ": " + new Gson().toJson(boreholeDepth));
        database.boreholeDepthDao().updateBoreholeDepth(updatedBoreholeDepth);
        for (BoreholeDepth boreholeDepth : database.boreholeDepthDao().getAll())
            System.out.println("{a}borehole depth #" + boreholeDepth.getId() + ": " + new Gson().toJson(boreholeDepth));
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
