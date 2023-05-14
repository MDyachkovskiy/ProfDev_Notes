package gb.com.profdev_notes.model.datasource

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase(){
    abstract fun noteDao(): NoteDao

    companion object {
        const val DB_NAME = "tbl_note.db"
    }

}