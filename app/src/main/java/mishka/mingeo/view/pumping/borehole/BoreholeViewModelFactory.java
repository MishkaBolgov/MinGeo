package mishka.mingeo.view.pumping.borehole;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.data.model.Borehole;

public class BoreholeViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private DataManager dataManager;

    @Inject
    public BoreholeViewModelFactory(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        return (T) new BoreholeViewModel(dataManager);
    }
}
