package mishka.mingeo.data.datamanager.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import mishka.mingeo.data.Pumping;

@Database(entities = {Pumping.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PumpingDao pumpingDao();
}
