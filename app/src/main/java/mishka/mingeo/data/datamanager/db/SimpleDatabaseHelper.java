package mishka.mingeo.data.datamanager.db;

import java.util.List;

import javax.inject.Inject;

import mishka.mingeo.data.Pumping;

public class SimpleDatabaseHelper implements DatabaseHelper {

    private AppDatabase database;

    @Inject
    public SimpleDatabaseHelper(AppDatabase database) {
        this.database = database;
    }

    @Override
    public void addPumping(Pumping pumping) {
        database.pumpingDao().addPumping(pumping);
    }

    @Override
    public List<Pumping> getAllPumpings() {
        return database.pumpingDao().getAll();
    }
}
