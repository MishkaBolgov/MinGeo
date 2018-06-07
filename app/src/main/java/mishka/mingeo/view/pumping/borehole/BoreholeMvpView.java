package mishka.mingeo.view.pumping.borehole;

import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.view.MvpView;

public interface BoreholeMvpView extends MvpView {
    void addBoreholeDepth(BoreholeDepth boreholeDepth);
}
