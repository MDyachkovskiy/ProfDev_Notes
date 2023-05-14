package gb.com.profdev_notes.model.datasource

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    // Method #1
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note): Long

    // Method #2
    @Update
    fun update(note: Note)

    // Method #3
    @Query("DELETE FROM tbl_note WHERE id = :id")
    fun deleteById(id: Int)

    // Method #4
    @Delete
    fun delete(note: Note)

    // Method #5
    @Query("SELECT * FROM tbl_note")
    fun getAllNotes(): LiveData<List<Note>>

}