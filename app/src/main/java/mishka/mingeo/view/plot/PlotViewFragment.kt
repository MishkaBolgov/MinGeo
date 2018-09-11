package mishka.mingeo.view.plot


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.fragment_plot_view.view.*

import mishka.mingeo.R
import mishka.mingeo.data.model.BoreholeDepth

class PlotViewFragment : Fragment(), PlotView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plot_view, container, false)

    }

    override fun plot(data: List<List<BoreholeDepth>>) {

        if (data.isEmpty())
            return

        val plotData: MutableList<ILineDataSet> = ArrayList()

        for ((index, boreholeData) in data.withIndex())
            if (boreholeData.isNotEmpty()) {
                val lineDataSet = getBoreholeDataSet(boreholeData, index)
                plotData.add(lineDataSet)
            }

        val lineData = LineData(plotData)

        view?.chart?.data = lineData
        val description = Description()
        description.text = ""
        view?.chart?.description = description
        view?.chart?.invalidate()
    }

    private fun getBoreholeDataSet(boreholeData: List<BoreholeDepth>, index: Int): ILineDataSet {
        val entries = ArrayList<Entry>()

        for (depth in boreholeData) {
            entries.add(Entry(depth.days, depth.depth))
        }
        val lineDataSet = LineDataSet(entries, "")
        lineDataSet.lineWidth = 4f
        lineDataSet.color = getLineColor(index)

        return lineDataSet
    }

    private fun getLineColor(index: Int): Int {
        return when (index) {
            0 -> context.resources.getColor(R.color.plotLineColor1)
            1 -> context.resources.getColor(R.color.plotLineColor2)
            else -> context.resources.getColor(R.color.plotLineColor3)
        }
    }


}
