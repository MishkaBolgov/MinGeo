package mishka.mingeo.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Pumping implements Serializable, DatabaseEntity{
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int getId() {
        return id;
    }
}
