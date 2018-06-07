package mishka.mingeo.view;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mishka.mingeo.MinGeoApplication;
import mishka.mingeo.R;
import mishka.mingeo.di.component.ActivityComponent;
import mishka.mingeo.di.component.ApplicationComponent;
import mishka.mingeo.di.component.DaggerActivityComponent;
import mishka.mingeo.di.module.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    protected ApplicationComponent getApplicationComponent(){
        return ((MinGeoApplication)getApplication()).getApplicationComponent();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerActivityComponent.Builder activityComponentBuilder = DaggerActivityComponent.builder();
        activityComponentBuilder.activityModule(new ActivityModule(this));
        activityComponentBuilder.applicationComponent(getApplicationComponent());

        activityComponent = activityComponentBuilder.build();
    }


    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
