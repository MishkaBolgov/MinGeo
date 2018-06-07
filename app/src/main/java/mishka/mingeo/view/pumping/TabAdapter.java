package mishka.mingeo.view.pumping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.view.pumping.borehole.BoreholeFragment;

public class TabAdapter extends FragmentStatePagerAdapter {

    private List<Borehole> boreholes;

    @Inject
    public TabAdapter(FragmentManager fm) {
        super(fm);
        boreholes = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        BoreholeFragment boreholeFragment = new BoreholeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("borehole", boreholes.get(position));
        boreholeFragment.setArguments(bundle);
        return boreholeFragment;
    }

    @Override
    public int getCount() {
        return boreholes.size();
    }

    public void update(List<Borehole> boreholes) {
        this.boreholes = boreholes;
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Скважина #" + position;
    }
}
