package mishka.mingeo.view.pumping.note

import java.io.File

interface RecordPlayer {
    fun play(path: String)
    fun stop()
}