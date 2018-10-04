package mishka.mingeo.di.component

import android.support.v4.app.Fragment
import dagger.Component
import mishka.mingeo.di.ActivityScope
import mishka.mingeo.di.module.BoreholeModule
import mishka.mingeo.view.plot.PlotViewFragment
import mishka.mingeo.view.pumping.borehole.BoreholeActivity

@Component(dependencies = [ActivityComponent::class], modules = [BoreholeModule::class])
@ActivityScope
interface BoreholeComponent {
    fun inject(activity: BoreholeActivity)
    fun inject(fragment: PlotViewFragment)
}