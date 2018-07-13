package mishka.mingeo.di.component;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import dagger.Component;
import mishka.mingeo.di.ActivityScope;
import mishka.mingeo.di.module.ActivityModule;

@Component(modules = {ActivityModule.class})
public interface ActivityComponent {
    FragmentActivity getActivity();
    FragmentManager getFragmentManager();
}
