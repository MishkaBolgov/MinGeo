package mishka.mingeo.data.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import org.joda.time.Instant;

@Entity
@TypeConverters(DateConverter.class)
public class BoreholeDepth implements DatabaseEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int depth;

    private int boreholeId;


    private Instant date;

    public BoreholeDepth() {
    }

    public BoreholeDepth(Borehole borehole, int depth) {
        this.depth = depth;
        this.boreholeId = borehole.getId();
        date = Instant.now();
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

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public float getMinutes() {
        return date.toDateTime().getSecondOfDay();
    }

    public String getMonthDayDate() {
        return date.toDateTime().toString("dd/mm");
    }
}
