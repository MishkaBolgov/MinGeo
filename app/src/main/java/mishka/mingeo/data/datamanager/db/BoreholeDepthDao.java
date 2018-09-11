package mishka.mingeo.data.datamanager.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.BoreholeDepth;

@Dao
public interface BoreholeDepthDao {
    @Insert
    long addBoreholeDepth(BoreholeDepth boreholeDepth);

    @Update
    void updateBoreholeDepth(BoreholeDepth boreholeDepth);

    @Query("SELECT * FROM boreholedepth WHERE id=:id")
    BoreholeDepth getBoreholeDepthById(int id);

    @Query("SELECT * FROM boreholedepth WHERE boreholeId=:boreholeId ORDER BY date")
    List<BoreholeDepth> getDepthsForBorehole(int boreholeId);

    @Query("SELECT * FROM boreholedepth")
    List<BoreholeDepth> getAll();

    @Query("SELECT * FROM boreholedepth WHERE boreholeId=:id")
    LiveData<List<BoreholeDepth>> getLiveDepthsForBorehole(int id);

    @Query("SELECT * FROM boreholedepth WHERE boreholeId=:id")
    List<BoreholeDepth> getRawDepthsForBorehole(int id);
}
