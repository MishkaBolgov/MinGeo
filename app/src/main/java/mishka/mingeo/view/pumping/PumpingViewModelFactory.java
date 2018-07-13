package mishka.mingeo.view.pumping;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.data.model.Pumping;

public class PumpingViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private DataManager dataManager;
    private Pumping pumping;

    @Inject
    public PumpingViewModelFactory(DataManager dataManager, Pumping pumping) {
        this.dataManager = dataManager;
        this.pumping = pumping;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PumpingViewModel(dataManager, pumping);
    }
}
