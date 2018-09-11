package mishka.mingeo.di.module

import dagger.Module
import dagger.Provides
import mishka.mingeo.data.model.Borehole

@Module
class BoreholeActivityModule(val borehole: Borehole) {
    
}