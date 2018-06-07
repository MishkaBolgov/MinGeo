package mishka.mingeo.view.pumpinglist;

import java.util.List;

import javax.inject.Inject;

import mishka.mingeo.data.model.Pumping;
import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.view.MvpView;

public class PumpingListPresenter implements PumpingListMvpPresenter, DataManager.OnPumpingFetchedListener {

    private DataManager dataManager;

    private PumpingListMvpView view;

    @Inject
    public PumpingListPresenter(final DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void setMvpView(MvpView view) {
        this.view = (PumpingListMvpView) view;
        updateView();
    }

    private void updateView() {
        dataManager.getAllPumpings(this);
    }

    @Override
    public void onAllPumpingsFetched(List<Pumping> pumpings) {
        view.updatePumpingsList(pumpings);
    }

    @Override
    public void onAddPumpingClick() {
        dataManager.createPumping(new DataManager.OnItemAddedListener<Pumping>() {
            @Override
            public void onItemCreated(Pumping pumping) {
                view.onPumpingSelected(pumping);
            }
        });
    }
}
