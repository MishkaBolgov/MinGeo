package mishka.mingeo.di.module;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import mishka.mingeo.view.pumping.PumpingActivity;
import mishka.mingeo.view.pumping.PumpingMvpPresenter;
import mishka.mingeo.view.pumping.PumpingPresenter;
import mishka.mingeo.view.pumping.SummaryFragment;
import mishka.mingeo.view.pumpinglist.PumpingListMvpPresenter;
import mishka.mingeo.view.pumpinglist.PumpingListPresenter;

@Module
public class ActivityModule {
    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    AppCompatActivity provideActivity(){
        return activity;
    }

    @Provides
    FragmentManager provideSupportFragmentManager(AppCompatActivity activity){
        return activity.getSupportFragmentManager();
    }

    @Provides
    PumpingListMvpPresenter providePumpingListPresenter(PumpingListPresenter pumpingListPrsenter) {
        return pumpingListPrsenter;
    }

    @Provides
    PumpingMvpPresenter providePumpingMvpPresenter(PumpingPresenter presenter){
        return presenter;
    }

}
