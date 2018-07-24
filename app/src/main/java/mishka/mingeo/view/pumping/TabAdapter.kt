package mishka.mingeo.view.pumping

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.util.SparseArray
import android.view.ViewGroup

import java.io.Serializable
import java.util.ArrayList

import javax.inject.Inject

import mishka.mingeo.data.model.Borehole
import mishka.mingeo.data.model.Pumping
import mishka.mingeo.view.pumping.borehole.BoreholeFragment
import mishka.mingeo.view.pumping.pumpingsummary.PumpingSummaryFragment

class TabAdapter @Inject
constructor(fm: FragmentManager, private val pumping: Pumping) : FragmentStatePagerAdapter(fm), ViewPager.OnPageChangeListener {

    private var boreholes: List<Borehole>
    private var summaryFragment: PumpingSummaryFragment
    private val boreholeFragments: SparseArray<BoreholeFragment>

    init {
        boreholes = ArrayList()
        summaryFragment = createSummaryFragment()
        boreholeFragments = SparseArray()
    }


    override fun getItem(position: Int): Fragment {

        //        if (position == 0)
        //            return summaryFragment;
        //        else
        //        currentFragment.setBorehole
        return if (position == 0)
            summaryFragment
        else createBoreholeFragment(position - 1)

    }

    private fun createSummaryFragment(): PumpingSummaryFragment {
        val summaryFragment = PumpingSummaryFragment()

        val bundle = Bundle()
        bundle.putSerializable("pumping", pumping)
        summaryFragment.arguments = bundle

        this.summaryFragment = summaryFragment

        return summaryFragment
    }


    private fun createBoreholeFragment(position: Int): Fragment {
        val boreholeFragment = BoreholeFragment()

        val bundle = Bundle()
        bundle.putSerializable("borehole", boreholes!![position])
        boreholeFragment.arguments = bundle

        return boreholeFragment
    }

    override fun getCount(): Int {
        return boreholes.size + 1
    }

    fun updateTabs(boreholes: List<Borehole>) {
        this.boreholes = boreholes
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence {
        return if (position == 0)
            "Общее"
        else
            "Скважина №${boreholes[position - 1].id}"
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        if (position == 0)
            return
        else
            boreholeFragments.get(position - 1).onAppeared()
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position)
        if (position > 0)
            boreholeFragments.put(position - 1, fragment as BoreholeFragment)
        return fragment
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any) {
        //        boreholeFragments.remove(position);
        super.destroyItem(container, position, `object`)
    }
}
