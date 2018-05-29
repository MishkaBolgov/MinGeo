package mishka.mingeo.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Pumping {
    @PrimaryKey(autoGenerate = true)
    int id;

//    List<Borehole> boreholes;

    public Pumping() {
//        boreholes = new ArrayList<>();
//        boreholes.add(new Borehole());
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
//    public List<Borehole> getBoreholes() {
//        return boreholes;
//    }
//    public void setBoreholes(List<Borehole> boreholes) {
//        this.boreholes = boreholes;
//    }
}
