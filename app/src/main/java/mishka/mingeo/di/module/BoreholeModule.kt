package mishka.mingeo.di.module;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity

import dagger.Module;
import dagger.Provides;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.view.pumping.borehole.BoreholeViewModel;

@Module
class BoreholeModule(val borehole: Borehole) {

    @Provides
    fun provideBorehole() = borehole

    @Provides
    fun provideBoreholeViewModel(activty: AppCompatActivity, factory: BoreholeViewModel.Factory): BoreholeViewModel {
        return ViewModelProviders.of(activty, factory).get(BoreholeViewModel::class.java)
    }
}
