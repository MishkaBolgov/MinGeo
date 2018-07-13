package mishka.mingeo.di.component;

import dagger.Component;
import dagger.Subcomponent;
import mishka.mingeo.di.ActivityScope;
import mishka.mingeo.di.module.ActivityModule;
import mishka.mingeo.di.module.PumpingModule;
import mishka.mingeo.view.pumping.PumpingActivity;

@Component(dependencies = {ApplicationComponent.class, ActivityComponent.class},modules = PumpingModule.class)
public interface PumpingActivityComponent {
    void inject(PumpingActivity activity);
}
