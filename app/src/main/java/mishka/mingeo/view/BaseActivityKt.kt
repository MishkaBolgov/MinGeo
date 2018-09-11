package mishka.mingeo.view

import android.support.v7.app.AppCompatActivity
import mishka.mingeo.MinGeoApplication
import mishka.mingeo.di.ApplicationContext
import mishka.mingeo.di.component.ActivityComponent
import mishka.mingeo.di.component.ApplicationComponent
import mishka.mingeo.di.component.DaggerActivityComponent
import mishka.mingeo.di.module.ActivityModule

abstract class BaseActivityKt: AppCompatActivity(){
    fun getApplicationComponent(): ApplicationComponent {
        val application: MinGeoApplication = getApplication() as MinGeoApplication
        return application.applicationComponent
    }
    fun getActivityComponent(): ActivityComponent{
        return DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(ActivityModule(this))
                .build()
    }
}