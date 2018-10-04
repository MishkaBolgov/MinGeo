package mishka.mingeo.view.plot

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import mishka.mingeo.data.model.Borehole
import mishka.mingeo.data.model.BoreholeDepth
import mishka.mingeo.data.model.Pumping
import kotlin.math.log10

class BoreholePlotViewFragment : PlotViewFragment() {
    var borehole: Borehole? = null
    var pumping: Pumping? = null

    override fun getPlotData(): MutableList<ILineDataSet>? {
        borehole?.let {
            pumping = dataManager.getPumping(it)

            val depths = dataManager.getBoreholeDepths(it)

            if (depths.isEmpty())
                return null

            val lineDataSet = getBoreholeDataSet(depths)

            lineDataSet.color = getLineColor(0)

            return mutableListOf(lineDataSet)
        }

        return null
    }


    private fun getBoreholeDataSet(boreholeData: List<BoreholeDepth>): LineDataSet {
        val entries = ArrayList<Entry>()

        for (depth in boreholeData) {
            val arg = depth.logDaysFrom(pumping!!.startPumpingTime!!)
            val value =  depth.relativeDepth(borehole!!.initialDepth )
            entries.add(Entry(arg, value))
        }
        val lineDataSet = LineDataSet(entries, "")
        lineDataSet.lineWidth = 4f

        return lineDataSet
    }
}