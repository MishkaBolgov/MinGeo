package mishka.mingeo.view.pumping.borehole;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mishka.mingeo.R;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.di.component.BoreholeFragmentComponent;
import mishka.mingeo.di.component.DaggerBoreholeFragmentComponent;
import mishka.mingeo.di.module.BoreholeModule;
import mishka.mingeo.view.BaseActivity;
import mishka.mingeo.view.pumping.borehole.depthchart.DepthChartMvpView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoreholeFragment extends Fragment implements AddDepthDialog.AddDepthDialogListener {

    @BindView(R.id.depths)
    RecyclerView depthsRecyclerView;

    @BindView(R.id.add_depth)
    FloatingActionButton addDepth;

    @Inject
    DepthsAdapter adapter;

    @Inject
    BoreholeViewModel viewModel;

    //    Ужасная хуйня
    @Inject
    Borehole borehole;

    private DepthChartMvpView depthChartFragment;


    public BoreholeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_borehole, container, false);

        ButterKnife.bind(this, view);
        final Borehole borehole = (Borehole) getArguments().getSerializable("borehole");

        depthChartFragment = (DepthChartMvpView) getChildFragmentManager().findFragmentById(R.id.chart_fragment);

        BaseActivity baseActivity = (BaseActivity) getActivity();

        BoreholeFragmentComponent component = DaggerBoreholeFragmentComponent.builder()
                .applicationComponent(baseActivity.getApplicationComponent())
                .activityComponent(baseActivity.getActivityComponent())
                .boreholeModule(new BoreholeModule(borehole))
                .build();

        component.inject(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        depthsRecyclerView.setLayoutManager(layoutManager);
        depthsRecyclerView.setAdapter(adapter);

        setCurrentBoreholeAsCurrent();

        return view;
    }

    @OnClick(R.id.add_depth)
    void onAddDepthClick() {
        setCurrentBoreholeAsCurrent();
        AddDepthDialog addDepthDialog = new AddDepthDialog();
        addDepthDialog.setListener(this);
        addDepthDialog.show(getActivity().getFragmentManager(), "add_depth");
    }

    public void updateChart(List<BoreholeDepth> depths) {
        depthChartFragment.update(depths);
        depthChartFragment.showIfHidden();
    }


    @Override
    public void onDepthSet(int depth) {
        setCurrentBoreholeAsCurrent();
        viewModel.onNewDepthValueSet(depth);
    }

    /*
    Вот эта хуйня с установкой текущей скважины в ViewModel - просто пиздец
    Так же как и хранение объекта скважины в Activity
    todo: разобраться с этим
     */
    public void onAppeared() {
        setCurrentBoreholeAsCurrent();
    }

    private void setCurrentBoreholeAsCurrent(){
        viewModel.setBorehole(borehole);
        viewModel.getDepths(borehole).removeObservers(this);
        viewModel.getDepths(borehole).observe(this, new Observer<List<BoreholeDepth>>() {
            @Override
            public void onChanged(@Nullable List<BoreholeDepth> boreholeDepths) {
                adapter.updateBoreholeDepths(boreholeDepths);
            }
        });
    }
}
