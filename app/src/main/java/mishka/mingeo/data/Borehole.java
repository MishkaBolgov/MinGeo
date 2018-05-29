package mishka.mingeo.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

@Entity
public class Borehole {
    @PrimaryKey(autoGenerate = true)
    int id;
    int distanceFromCentral;
    List<DepthGauging> depthGaugings;

    public Borehole() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getDistanceFromCentral() {
        return distanceFromCentral;
    }
    public void setDistanceFromCentral(int distanceFromCentral) {
        this.distanceFromCentral = distanceFromCentral;
    }
    public List<DepthGauging> getDepthGaugings() {
        return depthGaugings;
    }
    public void setDepthGaugings(List<DepthGauging> depthGaugings) {
        this.depthGaugings = depthGaugings;
    }
}
