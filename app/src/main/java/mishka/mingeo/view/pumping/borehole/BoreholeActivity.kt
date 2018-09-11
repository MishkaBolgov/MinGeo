package mishka.mingeo.view.pumping.borehole

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_borehole.*
import kotlinx.android.synthetic.main.floating_button.*
import mishka.mingeo.R
import mishka.mingeo.data.model.Borehole
import mishka.mingeo.data.model.BoreholeDepth
import mishka.mingeo.di.component.DaggerBoreholeComponent
import mishka.mingeo.di.module.BoreholeModule
import mishka.mingeo.view.BaseActivityKt
import mishka.mingeo.view.dialog.CreateDepthDialog
import mishka.mingeo.view.dialog.SetValueDialog
import mishka.mingeo.view.plot.PlotView
import javax.inject.Inject

class BoreholeActivity : BaseActivityKt() {

    @Inject
    lateinit var adapter: DepthAdapter

    @Inject
    lateinit var viewModel: BoreholeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_borehole)

        val borehole = intent.getSerializableExtra("borehole") as Borehole

        val component = DaggerBoreholeComponent.builder()
                .activityComponent(getActivityComponent())
                .boreholeModule(BoreholeModule(borehole))
                .build()

        component.inject(this)

        rvDepths.adapter = adapter
        rvDepths.layoutManager = LinearLayoutManager(this)

        viewModel.getDepths().observe(this, object : Observer<List<BoreholeDepth>> {
            override fun onChanged(depths: List<BoreholeDepth>?) {
                depths?.let {
                    adapter.depths = it
                    (chartContainer as PlotView).plot(listOf(it))
                }
            }
        })


        btnAction.setOnClickListener { onCreateBoreholeDepthClick() }

    }

    private fun onCreateBoreholeDepthClick() {
        showCreateDepthDialog()
    }

    private fun showCreateDepthDialog() {
        val addDepthDialog = CreateDepthDialog()
        addDepthDialog.listener = object : SetValueDialog.SetValueListener {
            override fun onValueSet(value: Float) {
                viewModel.addDepth(value)
            }
        }
        addDepthDialog.show(fragmentManager, "add_depth")
    }
}
