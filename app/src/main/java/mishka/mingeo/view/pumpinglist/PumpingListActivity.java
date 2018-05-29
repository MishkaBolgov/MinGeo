package mishka.mingeo.view.pumpinglist;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mishka.mingeo.MinGeoApplication;
import mishka.mingeo.R;
import mishka.mingeo.data.Pumping;
import mishka.mingeo.di.component.ActivityComponent;
import mishka.mingeo.di.component.DaggerActivityComponent;
import mishka.mingeo.di.module.ActivityModule;
import mishka.mingeo.view.BaseActivity;
import mishka.mingeo.view.pumping.PumpingActivity;

public class PumpingListActivity extends BaseActivity implements PumpingListMvpView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.pumping_list)
    RecyclerView pumpingList;

    @BindView(R.id.btn_add_pumping)
    FloatingActionButton btnAddPumping;


    @Inject
    PumpingListMvpPresenter presenter;

    @Inject
    PumpingListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pumping_list);

        DaggerActivityComponent.Builder activityComponentBuilder = DaggerActivityComponent.builder();

        activityComponentBuilder.activityModule(new ActivityModule(this));
        activityComponentBuilder.applicationComponent(getApplicationComponent());

        ActivityComponent activityComponent = activityComponentBuilder.build();
        activityComponent.inject(this);

        ButterKnife.bind(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        pumpingList.setLayoutManager(layoutManager);
        pumpingList.setAdapter(adapter);

        presenter.setMvpView(this);


        toolbar.setTitle(R.string.app_name);

//        onAddPumpingClick();


    }

    @OnClick(R.id.btn_add_pumping)
    void onAddPumpingClick(){
        Intent intent = new Intent(this, PumpingActivity.class);
        startActivity(intent);
    }


    @Override
    public void updatePumpingsList(List<Pumping> pumpings) {
        adapter.updatePumpings(pumpings);
    }
}
