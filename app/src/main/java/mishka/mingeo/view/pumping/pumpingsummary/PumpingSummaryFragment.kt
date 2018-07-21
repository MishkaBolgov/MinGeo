package mishka.mingeo.view.pumping.pumpingsummary


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife

import javax.inject.Inject

class PumpingSummaryFragment : Fragment() {


    @Inject
    lateinit var adapter: BoreholeSummaryAdapter

    @Inject
    lateinit var viewModel: PumpingSummaryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.fragment_pumping_summary, container, false)
//
//        ButterKnife.bind(this, view)


//        val component = DaggerPumpingSummaryComponent.builder().build();
//        component.inject(this)


//        rvBoreholesSummary.layoutManager= LinearLayoutManager(activity)
//        rvBoreholesSummary.adapter = adapter

//        viewModel

        return null
    }


}
