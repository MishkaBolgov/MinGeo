package mishka.mingeo.view.plot

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import mishka.mingeo.data.model.Borehole
import mishka.mingeo.data.model.BoreholeDepth
import mishka.mingeo.data.model.Pumping

class PumpingPlotViewFragment : PlotViewFragment() {
    var pumping: Pumping? = null

    override fun getPlotData(): MutableList<ILineDataSet>? {
        pumping?.let {

            var boreholes = dataManager.getBoreholesForPumping(it)
            val lines = mutableListOf<ILineDataSet>()


            for ((index, borehole) in boreholes.withIndex()) {
                val depths = dataManager.getBoreholeDepths(borehole)
                if (depths.isEmpty())
                    continue

                val lineDataSet = getBoreholeDataSet(depths, borehole)

                lineDataSet.color = getLineColor(index)

                lines.add(lineDataSet)
            }

            return lines
        }

        return null
    }


    private fun getBoreholeDataSet(boreholeData: List<BoreholeDepth>, borehole: Borehole): LineDataSet {
        val entries = ArrayList<Entry>()

        for (depth in boreholeData) {
            val arg = depth.logDaysFromDividedByDistance(pumping!!.startPumpingTime!!, borehole.distanceFromCentral)
//            val arg = depth.logDaysFrom(pumping!!.startPumpingTime!!)
            val value = depth.relativeDepth(borehole.initialDepth )
            entries.add(Entry(arg, value))
        }
        val lineDataSet = LineDataSet(entries, "")
        lineDataSet.lineWidth = 4f

        return lineDataSet
    }
}