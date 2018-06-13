package mishka.mingeo.view.pumping.pumpinginfo;

import android.provider.ContactsContract;

import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.Pumping;
import mishka.mingeo.view.MvpView;

public class PumpingSummaryPresenter implements PumpingSummaryMvpPresenter {
    private Pumping pumping;
    private DataManager dataManager;
    private PumpingSummaryMvpView view;

    @Inject
    public PumpingSummaryPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void setPumping(Pumping pumping) {
        this.pumping = pumping;
    }

    @Override
    public void setMvpView(MvpView view) {
        this.view = (PumpingSummaryMvpView) view;
    }

}
