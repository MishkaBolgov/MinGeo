package mishka.mingeo.view.pumping.borehole;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mishka.mingeo.R;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.view.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoreholeFragment extends BaseFragment implements BoreholeMvpView {

    @Inject
    BoreholeMvpPresenter presenter;

    @BindView(R.id.depths)
    RecyclerView depthsRecyclerView;

    @Inject
    DepthsAdapter adapter;



    public BoreholeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_borehole, container, false);

        ButterKnife.bind(this, view);

        getActivityComponent().inject(this);

        presenter.setMvpView(this);
        adapter.setBoreholeFragment(this);


        Borehole borehole = (Borehole) getArguments().getSerializable("borehole");
        presenter.setBorehole(borehole);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        depthsRecyclerView.setLayoutManager(layoutManager);
        depthsRecyclerView.setAdapter(adapter);

        return view;
    }

    @OnClick(R.id.add_depth)
    void onAddDepthClick(){
        presenter.addBoreholeDepth();
    }

    @Override
    public void addBoreholeDepth(BoreholeDepth boreholeDepth) {
        adapter.addBoreholeDepth(boreholeDepth);
    }

    public void onBoreholeDepthUpdate(BoreholeDepth boreholeDepth) {
        presenter.onBoreholeDepthUpdate(boreholeDepth);
    }
}
