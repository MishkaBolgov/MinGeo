package mishka.mingeo.view.pumping.borehole.depthchart;

import java.util.List;

import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.view.MvpView;

public interface DepthChartMvpView extends MvpView {
    void update(List<BoreholeDepth> depths);

    void showIfHidden();
}
