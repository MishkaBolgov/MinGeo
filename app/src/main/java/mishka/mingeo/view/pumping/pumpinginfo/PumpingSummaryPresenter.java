package mishka.mingeo.view.pumping.pumpinginfo;

import javax.inject.Inject;

import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.data.model.Pumping;

public class PumpingSummaryPresenter implements PumpingSummaryMvpPresenter {
    private Pumping pumping;
    private DataManager dataManager;
    private PumpingSummaryMvpView view;

//    @Inject
    public PumpingSummaryPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void setPumping(Pumping pumping) {
        this.pumping = pumping;
    }

}
