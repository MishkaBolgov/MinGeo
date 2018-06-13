package mishka.mingeo.view.pumping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.Pumping;
import mishka.mingeo.view.pumping.borehole.BoreholeFragment;
import mishka.mingeo.view.pumping.pumpinginfo.PumpingSummaryFragment;

public class TabAdapter extends FragmentStatePagerAdapter {

    private List<Borehole> boreholes;
    private Pumping pumping;
    private PumpingSummaryFragment summaryFragment;

    @Inject
    public TabAdapter(FragmentManager fm) {
        super(fm);
        boreholes = new ArrayList<>();
        summaryFragment = createSummaryFragment();
    }

    public void setPumping(Pumping pumping) {
        this.pumping = pumping;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return summaryFragment;
        else
            return createBoreholeFragment(position-1);

    }

    private PumpingSummaryFragment createSummaryFragment() {
        PumpingSummaryFragment summaryFragment = new PumpingSummaryFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("boreholes", (Serializable) boreholes);
        bundle.putSerializable("pumping", pumping);
        summaryFragment.setArguments(bundle);

        this.summaryFragment = summaryFragment;

        return summaryFragment;
    }

    private Fragment createBoreholeFragment(int position) {
        BoreholeFragment boreholeFragment = new BoreholeFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("borehole", boreholes.get(position));
        boreholeFragment.setArguments(bundle);



        return boreholeFragment;
    }

    @Override
    public int getCount() {
        return boreholes.size() + 1;
    }

    public void updateTabs(List<Borehole> boreholes) {
        this.boreholes = boreholes;

        summaryFragment.updateBoreholeList(boreholes);
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
            return "Общее";
        else
        return "Скважина #" + position;
    }
}
