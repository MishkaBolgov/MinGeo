package mishka.mingeo.view.pumping;

import javax.inject.Inject;

import mishka.mingeo.view.MvpView;

public class PumpingPresenter implements PumpingMvpPresenter {

    @Inject
    public PumpingPresenter() {

    }


    @Override
    public void setMvpView(MvpView view) {

    }

    @Override
    public void onAddBoreholeClick() {
        System.out.println("add ");
    }
}
