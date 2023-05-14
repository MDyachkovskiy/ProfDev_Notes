package gb.com.profdev_notes.model.repository

import androidx.lifecycle.LiveData
import gb.com.profdev_notes.model.datasource.Note
import gb.com.profdev_notes.model.datasource.NoteDao
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
){

    //Method #1
    fun insert(note: Note){
        noteDao.insert(note)
    }

    //Method #2
    fun delete(note: Note){
        noteDao.delete(note)
    }

    //Method #3
    fun deleteById(id: Int){
        noteDao.deleteById(id)
    }

    //Method #4
    fun update(note: Note){
        noteDao.update(note)
    }

    //Method #5
    fun getAllNotes(): LiveData<List<Note>> {
        return noteDao.getAllNotes()
    }
}