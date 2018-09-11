package mishka.mingeo.view.plot

import mishka.mingeo.data.model.Borehole
import mishka.mingeo.data.model.BoreholeDepth

interface PlotView {
    fun plot(data: List<List<BoreholeDepth>>)
}