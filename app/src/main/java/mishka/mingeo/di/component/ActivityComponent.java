package mishka.mingeo.di.component;

import dagger.Component;
import mishka.mingeo.di.ActivityScope;
import mishka.mingeo.di.module.ActivityModule;
import mishka.mingeo.view.pumping.PumpingActivity;
import mishka.mingeo.view.pumping.borehole.BoreholeFragment;
import mishka.mingeo.view.pumping.pumpinginfo.PumpingSummaryFragment;
import mishka.mingeo.view.pumpinglist.PumpingListActivity;

@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
@ActivityScope
public interface ActivityComponent {
    void inject(PumpingListActivity activity);
    void inject(PumpingActivity activity);
    void inject(BoreholeFragment boreholeFragment);
    void inject(PumpingSummaryFragment pumpingSummaryFragment);
}
