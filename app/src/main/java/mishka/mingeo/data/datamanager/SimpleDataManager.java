package mishka.mingeo.data.datamanager;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mishka.mingeo.data.datamanager.db.BoreholeDao;
import mishka.mingeo.data.datamanager.db.BoreholeDepthDao;
import mishka.mingeo.data.datamanager.db.NoteDao;
import mishka.mingeo.data.datamanager.db.PumpingDao;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.data.model.Note;
import mishka.mingeo.data.model.Pumping;
import mishka.mingeo.data.datamanager.db.DatabaseHelper;

public class SimpleDataManager implements DataManager {
    private DatabaseHelper dbHelper;
    private PumpingDao pumpingDao;
    private BoreholeDao boreholeDao;
    private BoreholeDepthDao boreholeDepthDao;
    private NoteDao noteDao;

    @Inject
    public SimpleDataManager(DatabaseHelper dbHelper, PumpingDao pumpingDao, BoreholeDao boreholeDao, BoreholeDepthDao boreholeDepthDao, NoteDao noteDao) {
        this.dbHelper = dbHelper;
        this.pumpingDao = pumpingDao;
        this.boreholeDao = boreholeDao;
        this.boreholeDepthDao = boreholeDepthDao;
        this.noteDao = noteDao;

    }

    @Override
    public LiveData<List<Pumping>> getPumpings() {
        return pumpingDao.all();
    }

    @Override
    public long createPumping() {
        return pumpingDao.insertPumping(new Pumping());
    }

    @Override
    public Borehole createBorehole(final Pumping pumping) {
        Long id = boreholeDao.createBorehole(new Borehole(pumping));
        return boreholeDao.getBoreholeById(id);
    }


    @Override
    public LiveData<List<Borehole>> getLiveBoreholesForPumping(Pumping pumping) {
        return boreholeDao.getLiveBoreholesForPumping(pumping.getId());
    }

    @Override
    public LiveData<List<BoreholeDepth>> getBoreholeDepthsForBorehole(Borehole borehole) {
        return boreholeDepthDao.getLiveDepthsForBorehole(borehole.id);
    }

    @Override
    public void createBoreholeDepth(Borehole borehole, float depth) {
        boreholeDepthDao.addBoreholeDepth(new BoreholeDepth(borehole, depth));
    }


    @Override
    public void setPumpPowerForPumping(final Pumping pumping, final float power) {
        pumpingDao.updatePumpPowerForPumping(pumping.getId(), power);

    }

    @NotNull
    @Override
    public LiveData<Pumping> getPumpingById(int id) {
        return pumpingDao.getLivePumpingById(id);
    }

    @Override
    public void setDistanceForBorehole(@NotNull final Borehole borehole, final int distance) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                boreholeDao.updateDistance(borehole.getId(), distance);
                return null;
            }
        }.execute();
    }

    @NotNull
    @Override
    public LiveData<List<BoreholeDepth>> getLiveBoreholeDepths(@NotNull Borehole borehole) {
        return boreholeDepthDao.getLiveDepthsForBorehole(borehole.id);
    }

    @NotNull
    @Override
    public List<Borehole> getBoreholesForPumping(@NotNull Pumping pumping) {
        return boreholeDao.getBoreholesForPumping(pumping.getId());
    }

    @NotNull
    @Override
    public List<BoreholeDepth> getBoreholeDepths(@NotNull Borehole borehole) {
        return boreholeDepthDao.getDepthsForBorehole(borehole.id);
    }

    @NotNull
    @Override
    public List<List<BoreholeDepth>> getDepthsForPumping(@NotNull Pumping pumping) {
        List<List<BoreholeDepth>> all = new ArrayList<>();

        List<Borehole> boreholes = boreholeDao.getBoreholesForPumping(pumping.getId());
        for (Borehole borehole : boreholes) {
            all.add(boreholeDepthDao.getRawDepthsForBorehole(borehole.id));
        }

        return all;
    }

    @NotNull
    @Override
    public List<Pumping> getAllPumpings() {
        return pumpingDao.getAll();
    }


    @Override
    public void createNote( File file, Pumping pumping) {
        noteDao.insert(new Note(file.getPath(), pumping));
    }

    @NotNull
    @Override
    public LiveData<List<Note>> getNotesLive(Pumping pumping) {
        return noteDao.getAllLive(pumping.getId());
//        return noteDao.getAllLive();
    }
}
