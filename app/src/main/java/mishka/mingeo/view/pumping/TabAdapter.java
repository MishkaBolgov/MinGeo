package mishka.mingeo.view.pumping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.Pumping;
import mishka.mingeo.view.pumping.borehole.BoreholeFragment;
import mishka.mingeo.view.pumping.pumpinginfo.PumpingSummaryFragment;

public class TabAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener {

    private List<Borehole> boreholes;
    private Pumping pumping;
    private PumpingSummaryFragment summaryFragment;
    private SparseArray<BoreholeFragment> boreholeFragments;

    @Inject
    public TabAdapter(FragmentManager fm, Pumping pumping) {
        super(fm);
        boreholes = new ArrayList<>();
        summaryFragment = createSummaryFragment();
        this.pumping = pumping;
        boreholeFragments = new SparseArray<>();
    }


    @Override
    public Fragment getItem(int position) {
//        if (position == 0)
//            return summaryFragment;
//        else
        Fragment currentFragment = createBoreholeFragment(position);
//        currentFragment.setBorehole
        return currentFragment;

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
        return boreholes.size();
    }

    public void updateTabs(List<Borehole> boreholes) {
        this.boreholes = boreholes;

//        summaryFragment.updateBoreholeList(boreholes);
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        if(position == 0)
//            return "Общее";
//        else
        return "Скважина #" + boreholes.get(position).id;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        boreholeFragments.get(position).onAppeared();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BoreholeFragment fragment = (BoreholeFragment) super.instantiateItem(container, position);
        boreholeFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        boreholeFragments.remove(position);
        super.destroyItem(container, position, object);
    }
}
