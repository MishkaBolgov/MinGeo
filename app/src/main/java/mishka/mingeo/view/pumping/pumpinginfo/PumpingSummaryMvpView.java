package mishka.mingeo.view.pumping.pumpinginfo;

import java.util.List;

import mishka.mingeo.data.model.Borehole;

public interface PumpingSummaryMvpView {
    void updateBoreholeList(List<Borehole> boreholes);
}
