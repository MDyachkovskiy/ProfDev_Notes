package gb.com.profdev_notes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import gb.com.profdev_notes.databinding.FragmentEditBinding
import gb.com.profdev_notes.model.datasource.Note
import gb.com.profdev_notes.ui.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class EditFragment : DaggerFragment() {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private var argNote: Note? = null

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var noteViewModel: NoteViewModel

    companion object {
        fun newInstance(note: Note?) = EditFragment().apply{
            arguments = Bundle().apply{
                putParcelable(NOTE_TAG, note)
            }
        }
        private const val NOTE_TAG = "edit_frag_note"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareNoteForEditing()
        setupViewModel()
        binding.noteEditSaveButton.setOnClickListener{
            saveNoteToDatabase()
            requireActivity().supportFragmentManager
                .popBackStack()
        }
    }

    private fun setupViewModel() {
        noteViewModel =
            ViewModelProvider(this, viewModelProviderFactory)[NoteViewModel::class.java]
    }

    private fun saveNoteToDatabase() {
        if (validation()) {
            Toast.makeText(activity, "Заметка сохранена", Toast.LENGTH_SHORT).show()
            saveNote()
        } else {
            Toast.makeText(activity, "Заметка не сохранена", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validation(): Boolean {
        with(binding) {
            return !(noteEditTitle.text.isNullOrEmpty()
                    && noteEditDescription.text.isNullOrEmpty()
                    && noteEditTag.text.isNullOrEmpty())
        }
    }

    private fun saveNote() {
        val id: Int? = argNote?.id
        with(binding) {
            val note = Note(
                id!!, noteEditTitle.text.toString(),
                noteEditDescription.text.toString(),
                noteEditTag.text.toString()
            )
            if (noteEditTitle.text.isNullOrEmpty()) {
                note.title = "Пустой загаловок"
            } else {
                noteViewModel.update(note)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveNoteToDatabase()
        _binding = null
    }

    private fun prepareNoteForEditing() {
        argNote = arguments?.getParcelable(NOTE_TAG)
        with (binding) {
            argNote?.let {
                noteEditTitle.setText(it.title)
                noteEditDescription.setText(it.description)
                noteEditTag.setText(it.tag)
            }
        }
    }
}