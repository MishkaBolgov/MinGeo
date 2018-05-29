package mishka.mingeo.data.datamanager.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import mishka.mingeo.data.Pumping;

@Dao
public interface PumpingDao {

    @Insert
    void addPumping(Pumping pumping);

    @Query("SELECT * FROM pumping")
    List<Pumping> getAll();
}
