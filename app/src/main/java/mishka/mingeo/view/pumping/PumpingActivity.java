package mishka.mingeo.view.pumping;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import mishka.mingeo.MinGeoApplication;
import mishka.mingeo.R;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.Pumping;
import mishka.mingeo.di.component.DaggerPumpingActivityComponent;
import mishka.mingeo.di.component.PumpingActivityComponent;
import mishka.mingeo.di.module.PumpingModule;
import mishka.mingeo.view.BaseActivity;
import mishka.mingeo.view.BaseActivityKt;
import mishka.mingeo.view.pumping.pumpinginfo.BoreholeSummaryAdapter;
import mishka.mingeo.view.pumping.pumpingsummary.BoreholeSelectedListener;

public class PumpingActivity extends BaseActivityKt implements BoreholeSelectedListener {

    @BindView(R.id.borehole_pager)
    ViewPager boreholePager;

    @BindView(R.id.borehole_tabs)
    TabLayout tabs;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    TabAdapter adapter;

    @Inject
    PumpingViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pumping);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Pumping pumping = (Pumping) intent.getSerializableExtra("pumping");

        PumpingActivityComponent component = DaggerPumpingActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityComponent(getActivityComponent())
                .pumpingModule(new PumpingModule(pumping))
                .build();

        component.inject(this);


        boreholePager.setAdapter(adapter);
        boreholePager.addOnPageChangeListener(adapter);
        viewModel.getBoreholes().observe(this, new Observer<List<Borehole>>() {
            @Override
            public void onChanged(@Nullable List<Borehole> boreholes) {
                adapter.updateTabs(boreholes);
            }
        });

        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pumping_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_borehole)
            onCreateBoreholeClick();
        return super.onOptionsItemSelected(item);
    }

    public void updateBoreholes(List<Borehole> boreholes) {
        adapter.updateTabs(boreholes);
    }

    public void onCreateBoreholeClick() {
        viewModel.onCreateBoreholeClick();
    }


    @Override
    public void onBoreholeSelected(int position) {
        boreholePager.setCurrentItem(position);
    }
}
