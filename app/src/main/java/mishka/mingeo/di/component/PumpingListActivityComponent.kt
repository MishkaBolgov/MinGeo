package mishka.mingeo.di.component

import dagger.Component
import mishka.mingeo.di.ActivityScope
import mishka.mingeo.di.module.PumpingListModule
import mishka.mingeo.view.pumpinglist.PumpingListActivity

@Component(dependencies = [ActivityComponent::class], modules = [PumpingListModule::class])
@ActivityScope
interface PumpingListActivityComponent {
    fun inject(activity: PumpingListActivity)
}