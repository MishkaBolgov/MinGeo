package mishka.mingeo.view.pumping;

import mishka.mingeo.data.model.Pumping;
import mishka.mingeo.view.MvpPresenter;

public interface PumpingMvpPresenter extends MvpPresenter {

    void onAddBoreholeClick();
    void setPumping(Pumping pumping);
}
