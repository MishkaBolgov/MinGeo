package mishka.mingeo.data.datamanager.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import mishka.mingeo.data.model.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAll(): List<Note>

    @Insert
    fun insert(note: Note)

//    Async
    @Query("SELECT * FROM note")
    fun getAllAsync(): LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE pumpingId=:pumpingId")
    fun getByPumpingAsync(pumpingId: Long): LiveData<List<Note>>

}