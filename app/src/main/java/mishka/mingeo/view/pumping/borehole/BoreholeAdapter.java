package mishka.mingeo.view.pumping.borehole;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mishka.mingeo.data.Borehole;
import mishka.mingeo.view.pumping.SummaryFragment;

public class BoreholeAdapter extends FragmentStatePagerAdapter {

    private List<Borehole> boreholes;
    private SummaryFragment summaryFragment;

    @Inject
    public BoreholeAdapter(FragmentManager fm, SummaryFragment summaryFragment) {
        super(fm);
        this.summaryFragment = summaryFragment;
        boreholes = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return summaryFragment;
//        else {
//            BoreholeFragment boreholeFragment = new BoreholeFragment();
//            boreholeFragment.setBorehole(boreholes.get(position - 1));
//            return boreholeFragment;
//        }
        return null;
    }



    @Override
    public int getCount() {
        return boreholes.size() + 1;
    }
}
