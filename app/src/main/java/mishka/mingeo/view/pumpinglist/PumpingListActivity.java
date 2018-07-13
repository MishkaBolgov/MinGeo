package mishka.mingeo.view.pumpinglist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mishka.mingeo.MinGeoApplication;
import mishka.mingeo.R;
import mishka.mingeo.data.model.Pumping;
import mishka.mingeo.di.component.ActivityComponent;
import mishka.mingeo.di.component.DaggerActivityComponent;
import mishka.mingeo.di.component.DaggerPumpingListActivityComponent;
import mishka.mingeo.di.component.PumpingListActivityComponent;
import mishka.mingeo.di.module.ActivityModule;
import mishka.mingeo.di.module.PumpingListModule;
import mishka.mingeo.di.module.PumpingModule;
import mishka.mingeo.view.BaseActivity;
import mishka.mingeo.view.BaseActivityKt;
import mishka.mingeo.view.pumping.PumpingActivity;

public class PumpingListActivity extends BaseActivityKt {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.pumping_list)
    RecyclerView pumpingList;

    @BindView(R.id.btn_action)
    FloatingActionButton btnAddPumping;

    @Inject
    PumpingListAdapter adapter;

    @Inject
    PumpingListViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pumping_list);

        PumpingListActivityComponent component = DaggerPumpingListActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityComponent(getActivityComponent())
                .pumpingListModule(new PumpingListModule())
                .build();

        component.inject(this);

        adapter.setPumpingListActivity(this);

        ButterKnife.bind(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        pumpingList.setLayoutManager(layoutManager);
        pumpingList.setAdapter(adapter);

        toolbar.setTitle(R.string.app_name);

        viewModel.getPumpings().observe(this, new Observer<List<Pumping>>() {
            @Override
            public void onChanged(@Nullable List<Pumping> pumpings) {
                adapter.updatePumpings(pumpings);
            }
        });

        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pumping_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_db)
            deleteDatabase("database");
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_action)
    void onAddPumpingClick() {
        viewModel.onAddPumpingClick();
    }

    public void onPumpingSelected(Pumping pumping) {

        Intent intent = new Intent(this, PumpingActivity.class);
        intent.putExtra("pumping", pumping);
        startActivity(intent);
    }
}
