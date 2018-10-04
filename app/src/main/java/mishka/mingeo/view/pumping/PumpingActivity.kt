package mishka.mingeo.view.pumping

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View

import javax.inject.Inject

import kotlinx.android.synthetic.main.activity_pumping.*
import kotlinx.android.synthetic.main.property.view.*
import kotlinx.android.synthetic.main.toolbar.*
import mishka.mingeo.R
import mishka.mingeo.data.model.Borehole
import mishka.mingeo.data.model.Pumping
import mishka.mingeo.di.component.DaggerPumpingActivityComponent
import mishka.mingeo.di.component.PumpingActivityComponent
import mishka.mingeo.di.module.PumpingModule
import mishka.mingeo.view.BaseActivityKt
import mishka.mingeo.view.dialog.PumpPowerDialog
import mishka.mingeo.view.dialog.SetValueDialog
import mishka.mingeo.view.plot.PlotView
import mishka.mingeo.view.plot.PlotViewFragment
import mishka.mingeo.view.plot.PumpingPlotViewFragment
import mishka.mingeo.view.pumping.note.NotesFragment

class PumpingActivity : BaseActivityKt() {

    @Inject
    lateinit var viewModel: PumpingViewModel

    @Inject
    lateinit var adapter: BoreholeAdapter

    lateinit var component: PumpingActivityComponent

    private lateinit var notesFragment: NotesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pumping)

        setSupportActionBar(toolbar)
        toolbar.title = "Выкачка"

        hideChart()

        val pumping = intent.getSerializableExtra("pumping") as Pumping

        component = DaggerPumpingActivityComponent.builder()
                .activityComponent(getActivityComponent())
                .pumpingModule(PumpingModule(pumping))
                .build()

        component.inject(this)
        component.inject(chartContainer as PlotViewFragment)
        (chartContainer as PumpingPlotViewFragment).pumping = pumping

        rvBoreholes.adapter = adapter
        rvBoreholes.layoutManager = LinearLayoutManager(this)

        viewModel.boreholes.observe(this, Observer {
            adapter.boreholes = it ?: ArrayList()
        })

        viewModel.getPumping().observe(this, Observer {
            pumpPower.propertyValue.text = it?.pumpPower.toString()
            adapter.centralBoreholeId = it?.centralBoreholeId
            if(it?.startPumpingTime != null){
                pumpingStartedLabel.visibility = View.VISIBLE
                btnStartPumping.visibility = View.GONE
            }

        })

        if(viewModel.isPumpingStarted()){
            pumpingStartedLabel.visibility = View.VISIBLE
        } else {
            btnStartPumping.visibility = View.VISIBLE
        }

        btnAddBorehole.setOnClickListener {
            onCreateBoreholeClick()
        }

        pumpPower.propertyName.text = "Мощность насоса"
        pumpPower.setOnClickListener { showSetPumpPowerDialog() }

        notesFragment = supportFragmentManager.findFragmentById(R.id.notesFragment) as NotesFragment

        btnStartPumping.setOnClickListener { onStartPumpingClick() }
    }

    private fun onStartPumpingClick() {
        viewModel.saveStartPumpingTime()
    }


    private fun hideChart() {
        chartContainer.view?.visibility = View.GONE
    }

    private fun showChart() {
        chartContainer.view?.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        val data = viewModel.getSummaryDepth()

        for (boreholeDepths in data)
            if (boreholeDepths.isNotEmpty()) {
                showChart()
                break
            }

        (chartContainer as PlotView).update()
    }

    private fun showSetPumpPowerDialog() {
        PumpPowerDialog().apply {
            listener = object : SetValueDialog.SetValueListener {
                override fun onValueSet(value: Float) {
                    viewModel.setPumpPower(value)
                }
            }
        }.show(fragmentManager, "set_pump_power")
    }

    private fun onCreateBoreholeClick() = viewModel.onCreateBoreholeClick()

    fun markBoreholeAsCentral(borehole: Borehole?) {
        if (borehole != null)
            viewModel.setCentralBorehole(borehole)
    }


}
