package mishka.mingeo.view.pumpinglist;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import mishka.mingeo.data.datamanager.DataManager;

public class PumpingListViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private DataManager dataManager;

    @Inject
    public PumpingListViewModelFactory(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PumpingListViewModel(dataManager);
    }
}
