package mishka.mingeo.data.datamanager.db;

import java.util.List;

import mishka.mingeo.data.Pumping;

public interface DatabaseHelper {
    void addPumping(Pumping pumping);
    List<Pumping> getAllPumpings();
}
