package mishka.mingeo.data.datamanager.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.data.model.Pumping;

@Database(entities = {Pumping.class, Borehole.class, BoreholeDepth.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PumpingDao pumpingDao();
    public abstract BoreholeDao boreholeDao();
    public abstract BoreholeDepthDao boreholeDepthDao();
}
