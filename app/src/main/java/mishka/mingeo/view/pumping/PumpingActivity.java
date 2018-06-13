package mishka.mingeo.view.pumping;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import mishka.mingeo.R;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.Pumping;
import mishka.mingeo.view.BaseActivity;
import mishka.mingeo.view.pumping.pumpinginfo.BoreholeSummaryAdapter;

public class PumpingActivity extends BaseActivity implements PumpingMvpView, BoreholeSummaryAdapter.BoreholeSummaryViewHolder.OnBoreholeClickListener {

    @BindView(R.id.borehole_pager)
    ViewPager boreholePager;

    @BindView(R.id.borehole_tabs)
    TabLayout tabs;


    @Inject
    TabAdapter adapter;

    @Inject
    PumpingMvpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pumping);

        Intent intent = getIntent();
        Pumping pumping = (Pumping) intent.getSerializableExtra("pumping");

        getActivityComponent().inject(this);

        adapter.setPumping(pumping);


        ButterKnife.bind(this);
        boreholePager.setAdapter(adapter);


        presenter.setPumping(pumping);
        presenter.setMvpView(this);
    }


    @Override
    public void updateBoreholes(List<Borehole> boreholes) {
        adapter.updateTabs(boreholes);
    }

    public void onAddBoreholeClick() {
        presenter.onAddBoreholeClick();
    }

    @Override
    public void onClick(int position) {
        boreholePager.setCurrentItem(position);
    }
}
