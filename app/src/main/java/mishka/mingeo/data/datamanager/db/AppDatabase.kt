package mishka.mingeo.data.datamanager.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration

import mishka.mingeo.data.model.Borehole
import mishka.mingeo.data.model.BoreholeDepth
import mishka.mingeo.data.model.Note
import mishka.mingeo.data.model.Pumping

@Database(entities = [Pumping::class, Borehole::class, BoreholeDepth::class, Note::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pumpingDao(): PumpingDao
    abstract fun boreholeDao(): BoreholeDao
    abstract fun boreholeDepthDao(): BoreholeDepthDao
    abstract fun noteDao(): NoteDao
}

val MIGRATION_1_2 = object : Migration(1, 2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE pumping ADD COLUMN centralBoreholeId INT ")
    }
}