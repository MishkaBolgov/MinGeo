package mishka.mingeo.di.module;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import mishka.mingeo.view.pumping.pumpinginfo.BoreholeSummaryAdapter;

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
    FragmentManager provideSupportFragmentManager(){
        return activity.getSupportFragmentManager();
    }


    @Provides
    BoreholeSummaryAdapter provideBoreholeSummaryAdapter(){
        return new BoreholeSummaryAdapter();
    }

}
