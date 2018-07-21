package mishka.mingeo.di.component

import dagger.Component
import mishka.mingeo.di.module.PumpingSummaryModule
import mishka.mingeo.view.pumping.pumpingsummary.PumpingSummaryFragment

@Component(dependencies = [ApplicationComponent::class], modules = [PumpingSummaryModule::class])
interface PumpingSummaryComponent {
    fun inject(fragment: PumpingSummaryFragment)
}