package mishka.mingeo.data.datamanager.db;

import java.util.List;

import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.data.model.DatabaseEntity;
import mishka.mingeo.data.model.Pumping;

public interface DatabaseHelper {
    int addPumping(Pumping pumping);
    int addBorehole(Borehole borehole);

    Pumping getPumpingById(int id);
    Borehole getBoreholeById(int id);
    BoreholeDepth getBoreholeDepthById(int id);

    List<Borehole> getBoreholesForPumping(Pumping pumping);

    List<Pumping> getAllPumpings();

    int addBoreholeDepth(BoreholeDepth objectToAdd);

    void updateBoreholeDepth(BoreholeDepth updatedBoreholeDepth);

    List<BoreholeDepth> getBoreholeDepthsForBorehole(Borehole ownerEntity);
}
