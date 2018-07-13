package mishka.mingeo.di.component;

import dagger.Component;
import mishka.mingeo.di.ActivityScope;
import mishka.mingeo.di.module.PumpingListModule;
import mishka.mingeo.view.pumpinglist.PumpingListActivity;

@Component(dependencies = {ApplicationComponent.class, ActivityComponent.class}, modules = PumpingListModule.class)
public interface PumpingListActivityComponent {
    void inject(PumpingListActivity activity);
}
