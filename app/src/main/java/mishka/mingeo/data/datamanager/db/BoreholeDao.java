package mishka.mingeo.data.datamanager.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import mishka.mingeo.data.model.Borehole;

@Dao
public interface BoreholeDao {

    @Insert
    long addBorehole(Borehole borehole);

    @Query("SELECT * FROM borehole WHERE pumpingId=:pumpingId")
    List<Borehole> getBoreholesForPumping(int pumpingId);

    @Query("SELECT * FROM borehole WHERE id=:id")
    Borehole getBoreholeById(int id);
}
