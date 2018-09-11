package mishka.mingeo.di.module;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import mishka.mingeo.data.model.Pumping;
import mishka.mingeo.view.pumping.PumpingViewModel;
import mishka.mingeo.view.pumping.note.NotesViewModel;
import mishka.mingeo.view.pumping.note.RecordPlayer;
import mishka.mingeo.view.pumping.note.Recorder;
import mishka.mingeo.view.pumping.note.SimpleRecordPlayer;
import mishka.mingeo.view.pumping.note.SimpleRecorder;

@Module
public class PumpingModule {
    private Pumping pumping;

    public PumpingModule(Pumping pumping) {
        this.pumping = pumping;
    }

    @Provides
    Pumping providePumping() {
        return pumping;
    }


    @Provides
    PumpingViewModel providePumpingViewModel(AppCompatActivity activity, PumpingViewModel.Factory factory) {
        return ViewModelProviders.of(activity, factory).get(PumpingViewModel.class);
    }

    @Provides
    Recorder provideRecorder(SimpleRecorder recorder) {
        return recorder;
    }

    @Provides
    RecordPlayer provideRecordPlayer(SimpleRecordPlayer recordPlayer){
        return recordPlayer;
    }

    @Provides
    NotesViewModel provideNotesViewModel(AppCompatActivity activity, NotesViewModel.Factory factory){
        return ViewModelProviders.of(activity, factory).get(NotesViewModel.class);
    }

}
