package mishka.mingeo.view.pumpinglist;

import java.util.List;

import mishka.mingeo.data.Pumping;
import mishka.mingeo.view.MvpView;

public interface PumpingListMvpView extends MvpView{
    void updatePumpingsList(List<Pumping> pumpings);
}