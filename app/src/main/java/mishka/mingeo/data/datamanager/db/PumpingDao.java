package mishka.mingeo.data.datamanager.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import mishka.mingeo.data.model.Pumping;

@Dao
public interface PumpingDao {

    @Insert
    long addPumping(Pumping pumping);

    @Query("SELECT * FROM pumping")
    List<Pumping> getAll();

    @Query("SELECT * FROM pumping WHERE id=:id")
    Pumping getPumpingById(int id);
}
