package mishka.mingeo.di.module;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import mishka.mingeo.view.pumping.borehole.BoreholePresenter;
import mishka.mingeo.view.pumping.pumpinginfo.BoreholeSummaryAdapter;

@Module
public class ActivityModule {
    private FragmentActivity activity;

    public ActivityModule(FragmentActivity activity) {
        this.activity = activity;
    }

    @Provides
    FragmentActivity provideActivity(){
        return activity;
    }

    @Provides
    FragmentManager provideSupportFragmentManager(FragmentActivity activity){
        return activity.getSupportFragmentManager();
    }


    @Provides
    BoreholeSummaryAdapter provideBoreholeSummaryAdapter(){
        return new BoreholeSummaryAdapter();
    }

}
