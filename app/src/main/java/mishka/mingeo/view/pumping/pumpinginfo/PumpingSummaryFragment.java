package mishka.mingeo.view.pumping.pumpinginfo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mishka.mingeo.R;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.Pumping;
import mishka.mingeo.view.BaseFragment;
import mishka.mingeo.view.pumping.PumpingActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class PumpingSummaryFragment extends BaseFragment implements PumpingSummaryMvpView{

    @BindView(R.id.rv_borehole_summary)
    RecyclerView rvBoreholeSummary;

    @Inject
    BoreholeSummaryAdapter adapter;

    @Inject
    PumpingSummaryMvpPresenter presenter;


    public PumpingSummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pumping_summary, container, false);

        getActivityComponent().inject(this);
        ButterKnife.bind(this, view);

        presenter.setMvpView(this);

        Pumping pumping = (Pumping) getArguments().getSerializable("pumping");
        presenter.setPumping(pumping);

        List<Borehole> boreholes = (List<Borehole>) getArguments().getSerializable("boreholes");
        adapter.setBoreholes(boreholes);
        adapter.setListener((BoreholeSummaryAdapter.BoreholeSummaryViewHolder.OnBoreholeClickListener)getActivity());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        rvBoreholeSummary.setLayoutManager(layoutManager);
        rvBoreholeSummary.setAdapter(adapter);

        return view;
    }

    @OnClick(R.id.btn_action)
    void onAddBoreholeClick(){
        ((PumpingActivity)getActivity()).onAddBoreholeClick();
    }

    @Override
    public void updateBoreholeList(List<Borehole> boreholes) {
        adapter.setBoreholes(boreholes);
    }
}
