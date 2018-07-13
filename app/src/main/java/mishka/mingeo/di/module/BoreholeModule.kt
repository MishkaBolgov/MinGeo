package mishka.mingeo.di.module;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

import dagger.Module;
import dagger.Provides;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.view.pumping.borehole.BoreholeViewModelFactory;
import mishka.mingeo.view.pumping.borehole.BoreholeViewModel;

@Module
class BoreholeModule(val borehole: Borehole) {

    @Provides
    fun provideBorehole(): Borehole {
        return borehole
    }

    @Provides
    fun provideBoreholeViewModel(fragmentActivity: FragmentActivity, factory: BoreholeViewModelFactory): BoreholeViewModel {
        val vm = ViewModelProviders.of(fragmentActivity, factory).get(BoreholeViewModel::class.java)
        vm.borehole = borehole
        return vm
    }
}
