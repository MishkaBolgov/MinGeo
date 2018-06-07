package mishka.mingeo.view.pumping;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mishka.mingeo.R;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.Pumping;
import mishka.mingeo.view.BaseActivity;

public class PumpingActivity extends BaseActivity implements PumpingMvpView {

    @BindView(R.id.borehole_pager)
    ViewPager boreholePager;


    @Inject
    TabAdapter adapter;

    @Inject
    PumpingMvpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pumping);

        Intent intent = getIntent();


        getActivityComponent().inject(this);


        ButterKnife.bind(this);
        boreholePager.setAdapter(adapter);

        Pumping pumping = (Pumping) intent.getSerializableExtra("pumping");
        presenter.setPumping(pumping);
        presenter.setMvpView(this);

    }

    @OnClick(R.id.btn_add_pumping)
    void onAddBoreholeClick() {
        presenter.onAddBoreholeClick();
    }

    @Override
    public void updateBoreholes(List<Borehole> boreholes) {
        adapter.update(boreholes);
    }
}
