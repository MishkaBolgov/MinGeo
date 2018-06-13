package mishka.mingeo.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.data.datamanager.SimpleDataManager;
import mishka.mingeo.data.datamanager.asyncdboperation.AsyncDbOperationManager;
import mishka.mingeo.data.datamanager.asyncdboperation.SimpleAsyncDbOperationManager;
import mishka.mingeo.data.datamanager.db.AppDatabase;
import mishka.mingeo.data.datamanager.db.DatabaseHelper;
import mishka.mingeo.data.datamanager.db.SimpleDatabaseHelper;
import mishka.mingeo.di.ApplicationContext;
import mishka.mingeo.di.DatabaseName;
@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(SimpleDataManager dataManager) {
        return dataManager;
    }

    @Provides
    AppDatabase provideAppDatabase(@ApplicationContext Context context, @DatabaseName String databaseName) {
        return Room.databaseBuilder(context, AppDatabase.class, databaseName).build();
    }

    @Provides
    @DatabaseName
    String provideDatabaseName(){
        return "database";
    }

    @Provides
    DatabaseHelper provideDatabaseHelper(SimpleDatabaseHelper databaseHelper){
        return databaseHelper;
    }

    @Provides
    AsyncDbOperationManager provideAsyncDbOperationManager(SimpleAsyncDbOperationManager asyncDbOperationManager){
        return asyncDbOperationManager;
    }
}
