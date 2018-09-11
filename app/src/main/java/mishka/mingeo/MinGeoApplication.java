package mishka.mingeo;

import android.app.Application;

import javax.inject.Inject;

import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.di.component.ApplicationComponent;
import mishka.mingeo.di.component.DaggerApplicationComponent;
import mishka.mingeo.di.module.ApplicationModule;


public class MinGeoApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Inject
    DataManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        applicationComponent.inject(this);

//        deleteDatabase("database");

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
