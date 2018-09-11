package mishka.mingeo.view.pumping.note

import android.media.MediaRecorder
import android.os.Environment
import mishka.mingeo.data.datamanager.DataManager
import mishka.mingeo.data.model.Pumping
import org.joda.time.Instant
import java.io.File
import javax.inject.Inject

class SimpleRecorder @Inject constructor(val pumping: Pumping, val dataManager: DataManager): Recorder {
    private var recorder = MediaRecorder()
    private lateinit var file: File
    init {
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
    }

    private fun setOutputFile() {
        file = File(Environment.getExternalStorageDirectory(), "/test_record_${Instant.now().millis}.3gp")
    }

    override fun startRecording() {
        setOutputFile()
        recorder = MediaRecorder()
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        recorder.setOutputFile(file.path)
        recorder.prepare()

        recorder.start()
    }

    override fun stopRecording() {
        recorder.stop()
        dataManager.createNote(file, pumping)
    }
}