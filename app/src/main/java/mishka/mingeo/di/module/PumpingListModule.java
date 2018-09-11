package mishka.mingeo.di.module;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import mishka.mingeo.view.pumpinglist.PumpingListViewModel;

@Module
public class PumpingListModule {
    @Provides
    PumpingListViewModel providePumpingListViewModel(AppCompatActivity activity, PumpingListViewModel.Factory factory){
        return ViewModelProviders.of(activity, factory).get(PumpingListViewModel.class);
    }
}
