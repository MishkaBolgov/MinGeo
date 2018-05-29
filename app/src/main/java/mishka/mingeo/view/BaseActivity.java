package mishka.mingeo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mishka.mingeo.MinGeoApplication;
import mishka.mingeo.R;
import mishka.mingeo.di.component.ApplicationComponent;

public abstract class BaseActivity extends AppCompatActivity {
    protected ApplicationComponent getApplicationComponent(){
        return ((MinGeoApplication)getApplication()).getApplicationComponent();
    }
}
