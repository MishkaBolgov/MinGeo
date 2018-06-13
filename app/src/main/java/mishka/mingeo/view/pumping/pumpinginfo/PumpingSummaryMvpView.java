package mishka.mingeo.view.pumping.pumpinginfo;

import java.util.List;

import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.view.MvpView;

public interface PumpingSummaryMvpView extends MvpView{
    void updateBoreholeList(List<Borehole> boreholes);
}
