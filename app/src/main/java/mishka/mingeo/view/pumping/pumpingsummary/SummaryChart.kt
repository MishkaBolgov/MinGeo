package mishka.mingeo.view.pumping.pumpingsummary

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
import kotlinx.android.synthetic.main.fragment_summary_chart.*
import mishka.mingeo.R
import mishka.mingeo.data.datamanager.DataManager
import mishka.mingeo.data.model.Borehole
import mishka.mingeo.data.model.BoreholeDepth

class SummaryChart : Fragment() {
    lateinit var dataManager: DataManager


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_summary_chart, container, false)

        view?.visibility = View.GONE

        return view
    }

    val chartData: MutableMap<Borehole, List<BoreholeDepth>> = HashMap()

    fun updateBoreholeData(borehole: Borehole, depths: List<BoreholeDepth>) {
        if (depths.isNotEmpty())
            chartData[borehole] = depths
    }

    fun update() {
        view?.visibility = View.VISIBLE

        val data: MutableList<ILineDataSet> = ArrayList()

        for (entry in chartData.entries)
            data.add(getBoreholeLineDataSet(entry.key))

        val lineData = LineData(data)

        val description = Description()
        description.text = ""
        chart.description = description
        chart.data = lineData
        chart.invalidate()
    }

    private fun getBoreholeLineDataSet(borehole: Borehole): LineDataSet {
        val entries = ArrayList<Entry>()

        for (depth in chartData[borehole]!!.iterator())
            entries.add(Entry(depth.minutes, depth.depth.toFloat()))

        val lineDataSet = LineDataSet(entries, "Глубина/Время")
        lineDataSet.lineWidth = 4f
        lineDataSet.setColor(resources.getColor(R.color.colorSecondary))
        lineDataSet.setCircleColor(resources.getColor(R.color.colorPrimary))

        return lineDataSet
    }
}