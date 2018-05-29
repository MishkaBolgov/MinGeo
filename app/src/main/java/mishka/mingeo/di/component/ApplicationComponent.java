package mishka.mingeo.di.component;

import dagger.Component;
import mishka.mingeo.MinGeoApplication;
import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.di.module.ApplicationModule;

@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MinGeoApplication application);
    DataManager getDataManager();
}
