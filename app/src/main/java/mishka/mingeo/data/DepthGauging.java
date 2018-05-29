package mishka.mingeo.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.joda.time.Instant;

@Entity
public class DepthGauging {

    @PrimaryKey(autoGenerate = true)
    int id;

    String gaugingAt;

    int depth;


    public DepthGauging() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getGaugingAt() {
        return gaugingAt;
    }
    public void setGaugingAt(String gaugingAt) {
        this.gaugingAt = gaugingAt;
    }
    public int getDepth() {
        return depth;
    }
    public void setDepth(int depth) {
        this.depth = depth;
    }
}
