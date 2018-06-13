package mishka.mingeo.di.component;

import javax.inject.Singleton;

import dagger.Component;
import mishka.mingeo.MinGeoApplication;
import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.di.module.ApplicationModule;

@Component(modules = ApplicationModule.class)
@Singleton
public interface ApplicationComponent {
    void inject(MinGeoApplication application);
    DataManager getDataManager();
}
