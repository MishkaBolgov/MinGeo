package mishka.mingeo.di.component

import dagger.Component
import mishka.mingeo.data.model.Pumping
import mishka.mingeo.di.ActivityScope
import mishka.mingeo.di.module.PumpingModule
import mishka.mingeo.view.pumping.PumpingActivity
import mishka.mingeo.view.pumping.note.NotesFragment

@Component(dependencies = [ActivityComponent::class], modules = [PumpingModule::class])
@ActivityScope
interface PumpingActivityComponent {
    fun inject(activity: PumpingActivity)
    fun inject(fragment: NotesFragment)
//    fun getPumping(): Pumping
}