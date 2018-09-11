package mishka.mingeo.view.pumping.borehole


import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_borehole.*
import kotlinx.android.synthetic.main.fragment_borehole.view.*


import javax.inject.Inject

import mishka.mingeo.R
import mishka.mingeo.data.model.Borehole
import mishka.mingeo.view.BaseActivityKt
import mishka.mingeo.view.pumping.borehole.depthchart.DepthChartFragment
import mishka.mingeo.view.pumping.borehole.depthchart.DepthChartMvpView

class BoreholeFragment : Fragment(), DistanceDialog.OnDistanceSetListener {

//    @Inject
    lateinit var adapter: DepthsAdapter

//    @Inject
    lateinit var viewModel: BoreholeViewModel

    //    Ужасная хуйня
    @Inject
    lateinit var borehole: Borehole

    lateinit var depthChartFragment: DepthChartMvpView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_borehole, container, false)

        val borehole = arguments.getSerializable("borehole") as Borehole

        depthChartFragment = childFragmentManager.findFragmentById(R.id.chart_fragment) as DepthChartFragment

        val baseActivity = activity as BaseActivityKt

//        val component = DaggerBoreholeFragmentComponent.builder()
//                .applicationComponent(baseActivity.getApplicationComponent())
//                .activityComponent(baseActivity.getActivityComponent())
//                .boreholeModule(BoreholeModule (borehole))
//                .build()
//
//        component.inject(this)

        val layoutManager = LinearLayoutManager(context)

        val rvDepths = view!!.findViewById<RecyclerView>(R.id.rvDepths)
        rvDepths.layoutManager = layoutManager
        rvDepths.adapter = adapter


        setCurrentBoreholeAsCurrent()

        val btnAddDepth = view.findViewById<FloatingActionButton>(R.id.btnAddDepth)

        btnAddDepth.setOnClickListener {onAddDepthClick()}

        view.tvDistance.setOnLongClickListener(object: View.OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {
                showDistanceDialog()
                return true
            }
        })

        view.tvDistance.text = borehole.distanceFromCentral.toString()

        return view
    }

    private fun showDistanceDialog() {
        val dialog = DistanceDialog()
        dialog.distanceSetListener = this
        dialog.show(fragmentManager, "distance_dialog")
    }


    override fun onDistanceSet(distance: Int) {
//        viewModel.updateDistance(distance)
    }

    fun onAddDepthClick()
    {
//        setCurrentBoreholeAsCurrent()
//        val addDepthDialog = CreateDepthDialog()
////        addDepthDialog.setListener(this)
//        addDepthDialog.show(activity.fragmentManager, "add_depth")
//        btnAddDepth.visibility = View.GONE

    }

//    override fun onDepthSet(depth: Float)
//    {
//        setCurrentBoreholeAsCurrent()
////        viewModel.onNewDepthValueSet(depth)
//        btnAddDepth.visibility = View.VISIBLE
//    }

    /*
    Вот эта хуйня с установкой текущей скважины в ViewModel - просто пиздец
    Так же как и хранение объекта скважины в Activity
    todo: разобраться с этим
     */
    fun onAppeared()
    {
        setCurrentBoreholeAsCurrent();
    }

    fun setCurrentBoreholeAsCurrent() {
//        viewModel.borehole = borehole
//        viewModel.getDepths(borehole).removeObservers(this)
//        viewModel.getDepths(borehole).observe(this, object: Observer<List<BoreholeDepth>> {
//            override fun onChanged(depths: List<BoreholeDepth>?) {
//                adapter.updateBoreholeDepths(depths)
//                depthChartFragment.update(depths)
//
//                if(depths?.size?:0 > 0)
//                    showChart()
//            }
//        })
    }

    private fun showChart() {
        chartContainer.visibility = View.VISIBLE
    }
}
