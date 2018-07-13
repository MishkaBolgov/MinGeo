package mishka.mingeo.di.module;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

import dagger.Module;
import dagger.Provides;
import mishka.mingeo.view.pumpinglist.PumpingListViewModel;
import mishka.mingeo.view.pumpinglist.PumpingListViewModelFactory;

@Module//(includes = ActivityModule.class)
public class PumpingListModule {


    @Provides
    PumpingListViewModel providePumpingListViewModel(FragmentActivity activity, PumpingListViewModelFactory factory){
        return ViewModelProviders.of(activity, factory).get(PumpingListViewModel.class);
    }
}
