package mishka.mingeo.view.pumping.borehole;

import java.util.List;

import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.view.MvpView;

public interface BoreholeMvpView extends MvpView {
    void addBoreholeDepth(BoreholeDepth boreholeDepth);
    void updateChart(List<BoreholeDepth> depths);

    void updateBoreholeDepthList(List<BoreholeDepth> items);
}
