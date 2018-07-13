package mishka.mingeo.di.component;

import dagger.Component;
import mishka.mingeo.di.ActivityScope;
import mishka.mingeo.di.module.BoreholeModule;
import mishka.mingeo.view.pumping.borehole.BoreholeFragment;

@Component(dependencies = {ApplicationComponent.class, ActivityComponent.class}, modules = BoreholeModule.class)
public interface BoreholeFragmentComponent {
    void inject(BoreholeFragment fragment);
}
