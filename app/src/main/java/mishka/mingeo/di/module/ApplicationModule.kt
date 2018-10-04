package mishka.mingeo.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import mishka.mingeo.data.datamanager.DataManager
import mishka.mingeo.data.datamanager.SimpleDataManager
import mishka.mingeo.data.datamanager.db.*
import mishka.mingeo.di.ApplicationContext
import mishka.mingeo.di.DatabaseName
import javax.inject.Singleton

@Module
class ApplicationModule(val application: Application) {
    @Provides
    @ApplicationContext
    fun provideApplicationContext(): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideDataManager(dataManager: SimpleDataManager): DataManager {
        return dataManager
    }

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context, @DatabaseName databaseName: String): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
                .addMigrations(MIGRATION_1_2)
                .allowMainThreadQueries()
                .build()
    }


    @Provides
    internal fun providePumpingDao(database: AppDatabase): PumpingDao {
        return database.pumpingDao()
    }

    @Provides
    internal fun provideBoreholeDao(database: AppDatabase): BoreholeDao {
        return database.boreholeDao()
    }

    @Provides
    internal fun provideBoreholeDepthdao(database: AppDatabase): BoreholeDepthDao {
        return database.boreholeDepthDao()
    }

    @Provides
    internal fun provideNoteDao(database: AppDatabase): NoteDao {
        return database.noteDao()
    }

    @Provides
    @DatabaseName
    internal fun provideDatabaseName(): String {
        return "database"
    }

}