package mishka.mingeo.view.pumping.borehole;

import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.view.MvpPresenter;

public interface BoreholeMvpPresenter extends MvpPresenter {
    void setBorehole(Borehole borehole);
    void createBoreholeDepth(int depth);

    void onBoreholeDepthUpdate(BoreholeDepth boreholeDepth);

    void onViewResume();
}
