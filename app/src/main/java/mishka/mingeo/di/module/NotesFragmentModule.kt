package mishka.mingeo.di.module

import dagger.Module
import dagger.Provides
import mishka.mingeo.data.model.Pumping
import mishka.mingeo.view.pumping.note.Recorder
import mishka.mingeo.view.pumping.note.SimpleRecorder

@Module
class NotesFragmentModule {
    @Provides
    fun provideRecorder(recorder: SimpleRecorder): Recorder = recorder
}