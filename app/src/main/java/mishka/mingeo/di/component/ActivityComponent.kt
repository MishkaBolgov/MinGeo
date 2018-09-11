package mishka.mingeo.di.component

import android.support.v7.app.AppCompatActivity

import dagger.Component
import mishka.mingeo.data.datamanager.DataManager
import mishka.mingeo.di.BaseActivityScope
import mishka.mingeo.di.module.ActivityModule
import mishka.mingeo.di.module.BoreholeActivityModule
import mishka.mingeo.di.module.PumpingListModule
import mishka.mingeo.di.module.PumpingModule

@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class, BoreholeActivityModule::class, PumpingModule::class, PumpingListModule::class])
@BaseActivityScope
interface ActivityComponent {
    fun getDataManager(): DataManager
    fun getActivity(): AppCompatActivity
}
