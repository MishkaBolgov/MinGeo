package mishka.mingeo.view.pumping.pumpingsummary


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_pumping_summary.view.*
import mishka.mingeo.R
import mishka.mingeo.data.model.Borehole
import mishka.mingeo.data.model.BoreholeDepth
import mishka.mingeo.data.model.Pumping
import mishka.mingeo.di.component.DaggerPumpingSummaryComponent
import mishka.mingeo.di.module.PumpingSummaryModule
import mishka.mingeo.view.BaseActivityKt

import javax.inject.Inject

class PumpingSummaryFragment : Fragment(), PumpPowerDialog.OnSetPumpPowerListener {


    @Inject
    lateinit var adapter: BoreholeSummaryAdapter

    @Inject
    lateinit var viewModel: PumpingSummaryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pumping_summary, container, false)

        val pumping = arguments.getSerializable("pumping") as Pumping

        val component = DaggerPumpingSummaryComponent.builder().applicationComponent((activity as BaseActivityKt).getApplicationComponent()).pumpingSummaryModule(PumpingSummaryModule(pumping)).build()
        component.inject(this)


        view.rvBoreholesSummary.layoutManager = LinearLayoutManager(activity)
        view.rvBoreholesSummary.adapter = adapter

        adapter.boreholeSelectedListener = activity as BoreholeSelectedListener

        val chart = childFragmentManager.findFragmentById(R.id.chartFragment) as SummaryChart

        viewModel.boreholes.observe(this, object : Observer<List<Borehole>> {
            override fun onChanged(boreholes: List<Borehole>?) {
                if (boreholes == null)
                    return

                adapter.boreholes = boreholes

                for (borehole in boreholes) {
                    viewModel.getDepths(borehole).observe(this@PumpingSummaryFragment, object : Observer<List<BoreholeDepth>> {
                        override fun onChanged(depths: List<BoreholeDepth>?) {
                            if (depths != null) {
                                chart.updateBoreholeData(borehole, depths)
                                chart.update()
                            }
                        }
                    })
                }

            }
        })

        println("pump power = " + pumping.pumpPower)

        viewModel.getPumping().observe(this, object : Observer<Pumping> {
            override fun onChanged(pumping: Pumping?) {
                if (pumping == null)
                    return

                view.tvPumpPower.text = "${pumping.pumpPower} кв/ч"
            }
        })

        view.tvPumpPower.setOnLongClickListener {
            onPumpPowerLongClick()
            true
        }

        return view
    }

    private fun onPumpPowerLongClick() {
        val pumpPowerDialog = PumpPowerDialog()
        pumpPowerDialog.listener = this
        pumpPowerDialog.show(activity.fragmentManager, "set_pump_power")
    }

    override fun onPumpPowerSet(value: Float) {
        viewModel.updatePumpPower(value)
    }

}
