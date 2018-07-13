package mishka.mingeo.view;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import mishka.mingeo.MinGeoApplication;
import mishka.mingeo.di.component.ActivityComponent;
import mishka.mingeo.di.component.ApplicationComponent;
import mishka.mingeo.di.component.DaggerActivityComponent;
import mishka.mingeo.di.module.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {
    public ApplicationComponent getApplicationComponent() {
        return ((MinGeoApplication) getApplication()).getApplicationComponent();
    }

    public ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder().activityModule(new ActivityModule(this)).build();
    }
}
