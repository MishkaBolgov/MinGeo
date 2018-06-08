package mishka.mingeo.data.model;

import android.arch.persistence.room.TypeConverter;

import org.joda.time.Instant;

public class DateConverter {
    @TypeConverter
    public static long instantToMillis(Instant instant){
        return instant.getMillis();
    }
    @TypeConverter
    public static Instant millisToInstant(long millis){
        return new Instant(millis);
    }
}
