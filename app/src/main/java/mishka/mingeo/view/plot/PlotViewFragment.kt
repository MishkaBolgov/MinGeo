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
import mishka.mingeo.data.datamanager.DataManager
import mishka.mingeo.data.model.BoreholeDepth
import javax.inject.Inject
import kotlin.math.ln
import kotlin.math.log
import kotlin.math.log10

abstract class PlotViewFragment : Fragment(), PlotView {
    @Inject
    lateinit var dataManager: DataManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plot_view, container, false)

    }

    override fun update() {

        val plotData: MutableList<ILineDataSet> = getPlotData() ?: return

        val lineData = LineData(plotData)

        view?.chart?.data = lineData
        val description = Description()
        description.text = ""
        view?.chart?.description = description
        view?.chart?.invalidate()
    }

    abstract fun getPlotData(): MutableList<ILineDataSet>?


    protected fun Float.relativeDepth(origin: Float): Float {
        return origin - this
    }

    protected fun getLineColor(index: Int): Int {
        return when (index) {
            0 -> context.resources.getColor(R.color.plotLineColor1)
            1 -> context.resources.getColor(R.color.plotLineColor2)
            2 -> context.resources.getColor(R.color.plotLineColor3)
            3 -> context.resources.getColor(R.color.plotLineColor4)
            4 -> context.resources.getColor(R.color.plotLineColor5)
            else -> context.resources.getColor(R.color.plotLineColor6)
        }
    }


}
