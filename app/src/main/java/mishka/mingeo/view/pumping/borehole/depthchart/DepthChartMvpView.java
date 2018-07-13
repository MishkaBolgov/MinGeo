package mishka.mingeo.view.pumping.borehole.depthchart;

import java.util.List;

import mishka.mingeo.data.model.BoreholeDepth;

public interface DepthChartMvpView {
    void update(List<BoreholeDepth> depths);

    void showIfHidden();
}
