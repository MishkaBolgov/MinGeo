package mishka.mingeo.di.component

import dagger.Component
import mishka.mingeo.di.ActivityScope
import mishka.mingeo.di.module.BoreholeModule
import mishka.mingeo.view.pumping.borehole.BoreholeActivity

@Component(dependencies = [ActivityComponent::class], modules = [BoreholeModule::class])
@ActivityScope
interface BoreholeComponent {
    fun inject(activity: BoreholeActivity)
}