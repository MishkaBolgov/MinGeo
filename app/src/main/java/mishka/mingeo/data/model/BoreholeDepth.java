package mishka.mingeo.data.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class BoreholeDepth implements DatabaseEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int depth;

    private int boreholeId;

    public BoreholeDepth() {
    }

    public BoreholeDepth(Borehole borehole) {
        this.depth = 0;
        this.boreholeId = borehole.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getBoreholeId() {
        return boreholeId;
    }

    public void setBoreholeId(int boreholeId) {
        this.boreholeId = boreholeId;
    }
}
