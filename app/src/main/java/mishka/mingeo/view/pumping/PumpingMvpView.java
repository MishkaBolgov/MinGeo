package mishka.mingeo.view.pumping;

import java.util.List;

import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.Pumping;
import mishka.mingeo.view.MvpView;

public interface PumpingMvpView extends MvpView{
    void updateBoreholes(List<Borehole> boreholes);
}
