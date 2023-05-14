package gb.com.profdev_notes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import gb.com.profdev_notes.model.datasource.Note
import gb.com.profdev_notes.model.repository.NoteRepository
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    // Method #1
    fun insert(note: Note) {
        return noteRepository.insert(note)
    }

    // Method #2
    fun delete(note: Note) {
        noteRepository.delete(note)
    }

    // Method #3
    fun deleteById(id:Int){
        noteRepository.deleteById(id)
    }

    // Method #4
    fun update(note: Note) {
        noteRepository.update(note)
    }

    // Method #5
    fun getAllNotes(): LiveData<List<Note>> {
        return noteRepository.getAllNotes()
    }

}