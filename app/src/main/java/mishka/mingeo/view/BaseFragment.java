package mishka.mingeo.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mishka.mingeo.R;
import mishka.mingeo.di.component.ActivityComponent;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment implements MvpView{

    private BaseActivity activity;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (BaseActivity) context;
    }

    protected ActivityComponent getActivityComponent() {
        return activity.getActivityComponent();
    }

}
