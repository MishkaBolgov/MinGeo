package mishka.mingeo.view.pumping.note

import android.media.MediaPlayer
import java.io.File
import javax.inject.Inject

class SimpleRecordPlayer @Inject constructor(): RecordPlayer {
    override fun play(path: String) {
        val mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(path)
        mediaPlayer.prepare()
        mediaPlayer.start()
    }

    override fun stop() {

    }
}