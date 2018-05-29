package mishka.mingeo.view.pumping;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewParent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mishka.mingeo.R;
import mishka.mingeo.di.component.ActivityComponent;
import mishka.mingeo.di.component.DaggerActivityComponent;
import mishka.mingeo.di.module.ActivityModule;
import mishka.mingeo.view.BaseActivity;
import mishka.mingeo.view.pumping.borehole.BoreholeAdapter;

public class PumpingActivity extends BaseActivity implements PumpingMvpView{

    @BindView(R.id.borehole_pager)
    ViewPager boreholePager;


    @Inject
    BoreholeAdapter adapter;

    @Inject
    PumpingMvpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pumping);

        DaggerActivityComponent.Builder activityComponentBuilder = DaggerActivityComponent.builder();
        activityComponentBuilder.activityModule(new ActivityModule(this));
        activityComponentBuilder.applicationComponent(getApplicationComponent());

        ActivityComponent activityComponent = activityComponentBuilder.build();

        activityComponent.inject(this);

        ButterKnife.bind(this);

        boreholePager.setAdapter(adapter);
    }

    @OnClick(R.id.btn_add_pumping)
    void onAddBoreholeClick(){
        presenter.onAddBoreholeClick();
    }

}
