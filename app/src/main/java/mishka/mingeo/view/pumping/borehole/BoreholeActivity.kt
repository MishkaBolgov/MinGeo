package mishka.mingeo.view.pumping.borehole

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_borehole.*
import kotlinx.android.synthetic.main.property.view.*
import kotlinx.android.synthetic.main.toolbar.*
import mishka.mingeo.R
import mishka.mingeo.data.model.Borehole
import mishka.mingeo.data.model.BoreholeDepth
import mishka.mingeo.di.component.DaggerBoreholeComponent
import mishka.mingeo.di.module.BoreholeModule
import mishka.mingeo.view.BaseActivityKt
import mishka.mingeo.view.dialog.*
import mishka.mingeo.view.plot.BoreholePlotViewFragment
import mishka.mingeo.view.plot.PlotView
import mishka.mingeo.view.plot.PlotViewFragment
import javax.inject.Inject

class BoreholeActivity : BaseActivityKt() {

    @Inject
    lateinit var adapter: DepthAdapter

    @Inject
    lateinit var viewModel: BoreholeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_borehole)
        setSupportActionBar(toolbar)
        toolbar.title = "Скважина"

        hideChart()

        val borehole = intent.getSerializableExtra("borehole") as Borehole

        val component = DaggerBoreholeComponent.builder()
                .activityComponent(getActivityComponent())
                .boreholeModule(BoreholeModule(borehole))
                .build()

        component.inject(this)
        component.inject(chartContainer as PlotViewFragment)

        rvDepths.adapter = adapter
        rvDepths.layoutManager = LinearLayoutManager(this)


        viewModel.getDepths().observe(this, object : Observer<List<BoreholeDepth>> {
            override fun onChanged(depths: List<BoreholeDepth>?) {
                depths?.let {
                    if (depths.isNotEmpty())
                        showChart()
                    adapter.depths = it
                    (chartContainer as PlotView).update()
                }
            }
        })

        viewModel.getBorehole().observe(this, Observer {
            it?.let {
                (chartContainer as BoreholePlotViewFragment).borehole = it
                distanceFromCentral.propertyValue.text = "${it.distanceFromCentral}"
                initialDepth.propertyValue.text = "${it.initialDepth}"
                headHeight.propertyValue.text = "${it.headHeight}"
            }
        })

        if(viewModel.isDepthAddingAvailable())
            btnAddDepth.visibility = View.VISIBLE

        distanceFromCentral.propertyName.text = "Расстояние до центральной скважины"
        distanceFromCentral.setOnClickListener { showSetDistanceFromCentralDialog() }

        initialDepth.propertyName.text = "Начальная глубина"
        initialDepth.setOnClickListener { showInitialDepthDialog() }


        headHeight.propertyName.text = "Высота оголовка"
        headHeight.setOnClickListener { showHeadDialog() }

        btnAddDepth.setOnClickListener { onCreateBoreholeDepthClick() }

    }

    override fun onResume() {
        super.onResume()
        (chartContainer as PlotView).update()
    }

    private fun hideChart() {
        chartContainer.view?.visibility = View.GONE
    }

    private fun showChart() {
        chartContainer.view?.visibility = View.VISIBLE
    }

    private fun showSetDistanceFromCentralDialog() {
        val dialog = DistanceFromCentralDialog()
        dialog.listener = object : SetValueDialog.SetValueListener {
            override fun onValueSet(value: Float) {
                viewModel.updateDistance(value)
            }
        }
        dialog.show(fragmentManager, "set_distance_dialog")
    }

    private fun showInitialDepthDialog() {
        InitialDepthDialog().apply {
            listener = object : SetValueDialog.SetValueListener {
                override fun onValueSet(value: Float) {
                    viewModel.setInitialDepth(value)
                }
            }
        }.show(fragmentManager, "set_initial_depth")
    }

    private fun showHeadDialog() {
        HeadHeightDialog().apply {
            listener = object : SetValueDialog.SetValueListener {
                override fun onValueSet(value: Float) {
                    viewModel.setHeadHeight(value)
                }
            }
        }.show(fragmentManager, "set_head_height")
    }

    private fun onCreateBoreholeDepthClick() {
        showCreateDepthDialog()
    }

    private fun showCreateDepthDialog() {
        DepthDialog().apply {
            listener = object : SetValueDialog.SetValueListener {
                override fun onValueSet(value: Float) {
                    viewModel.addDepth(value)
                }
            }
        }.show(fragmentManager, "add_depth")
    }
}
