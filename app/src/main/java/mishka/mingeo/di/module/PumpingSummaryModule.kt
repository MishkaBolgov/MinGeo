package mishka.mingeo.di.module

import dagger.Module
import dagger.Provides
import mishka.mingeo.data.model.Pumping

@Module
class PumpingSummaryModule(val pumping: Pumping) {

    @Provides
    fun providePumping() = pumping
}