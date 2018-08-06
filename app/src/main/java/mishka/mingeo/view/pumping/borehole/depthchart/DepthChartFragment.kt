package mishka.mingeo.view.pumping.borehole.depthchart


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

import java.util.ArrayList
import kotlinx.android.synthetic.main.fragment_borehole_depth.*
import mishka.mingeo.R
import mishka.mingeo.data.model.BoreholeDepth

class DepthChartFragment: Fragment(), DepthChartMvpView {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_borehole_depth, container, false)

        view?.visibility = View.GONE

        return view
    }

    override fun update(depths: MutableList<BoreholeDepth>) {
        if(depths.size == 0)
            return


        view?.visibility = View.VISIBLE

        val entries = ArrayList<Entry>()

        val originDepth = depths.first().depth

        for (depth in depths.iterator())
            entries.add(Entry(depth.minutes, depth.relativeDepth(originDepth)))


        val lineDataSet = LineDataSet(entries, "Глубина/Время")
        lineDataSet.lineWidth = 4f
//        lineDataSet.setColor(resources.getColor(R.color.colorSecondary))
//        lineDataSet.setCircleColor(resources.getColor(R.color.colorPrimary))

        val lineData = LineData(lineDataSet)

        val description = Description()
        description.text = ""
        chart.description = description
        chart.data = lineData
        chart.invalidate()

    }

    private fun getRelativeDepths(depths: MutableList<BoreholeDepth>): List<Float> {
        val firstDepth = depths.first().depth
        return depths.map { it.depth - firstDepth }
    }

}
