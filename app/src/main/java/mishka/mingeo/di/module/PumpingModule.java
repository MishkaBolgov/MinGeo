package mishka.mingeo.di.module;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

import dagger.Module;
import dagger.Provides;
import mishka.mingeo.data.model.Pumping;
import mishka.mingeo.view.pumping.PumpingViewModel;
import mishka.mingeo.view.pumping.PumpingViewModelFactory;

@Module
public class PumpingModule {
    private Pumping pumping;

    public PumpingModule(Pumping pumping) {
        this.pumping = pumping;
    }

    @Provides
    Pumping providePumping(){
        return pumping;
    }


    @Provides
    PumpingViewModel providePumpingViewModel(FragmentActivity activity, PumpingViewModelFactory factory){
        return ViewModelProviders.of(activity, factory).get(PumpingViewModel.class);
    }
}
