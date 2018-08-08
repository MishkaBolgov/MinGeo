package mishka.mingeo.di.module

import android.support.v4.app.FragmentActivity
import dagger.Module
import dagger.Provides
import mishka.mingeo.data.model.Pumping
import mishka.mingeo.view.pumping.pumpingsummary.BoreholeSelectedListener

@Module
class PumpingSummaryModule(val pumping: Pumping) {

    @Provides
    fun providePumping() = pumping

//    @Provides
//    fun provideBoreholeSelectedListener(activity: FragmentActivity): BoreholeSelectedListener = activity as BoreholeSelectedListener
}