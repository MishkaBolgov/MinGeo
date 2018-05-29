package mishka.mingeo.di.component;

import dagger.Component;
import mishka.mingeo.di.module.ActivityModule;
import mishka.mingeo.di.module.ApplicationModule;
import mishka.mingeo.view.pumping.PumpingActivity;
import mishka.mingeo.view.pumpinglist.PumpingListActivity;

@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(PumpingListActivity activity);
    void inject(PumpingActivity activity);

}
