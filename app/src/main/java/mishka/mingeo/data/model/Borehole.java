package mishka.mingeo.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
//        (foreignKeys = @ForeignKey(entity = Pumping.class,
//                                    parentColumns = "id",
//                                    childColumns = "pumpingId",
//                                    onDelete = CASCADE))
public class Borehole implements Serializable, DatabaseEntity  {
    @PrimaryKey(autoGenerate = true)
    public int id;

    private int pumpingId;

    private int distanceFromCentral;


    public Borehole() {
    }

    public Borehole(Pumping pumping) {
        this.pumpingId = pumping.getId();
        this.distanceFromCentral = new Random().nextInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPumpingId() {
        return pumpingId;
    }

    public void setPumpingId(int pumpingId) {
        this.pumpingId = pumpingId;
    }

    public int getDistanceFromCentral() {
        return distanceFromCentral;
    }

    public void setDistanceFromCentral(int distanceFromCentral) {
        this.distanceFromCentral = distanceFromCentral;
    }
}
