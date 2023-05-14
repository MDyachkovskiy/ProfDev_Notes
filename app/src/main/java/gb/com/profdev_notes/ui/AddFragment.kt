package gb.com.profdev_notes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import gb.com.profdev_notes.databinding.FragmentAddBinding
import gb.com.profdev_notes.model.datasource.Note
import gb.com.profdev_notes.ui.viewmodel.ViewModelProviderFactory

class AddFragment : DaggerFragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private var noteViewModel: NoteViewModel? = null

    companion object {
        fun newInstance() = AddFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        binding.noteAddSaveButton.setOnClickListener{
            saveNoteToDatabase()
            requireActivity().supportFragmentManager
                .popBackStack()
        }
    }

    private fun saveNoteToDatabase() {
        if (validations()){
            Toast.makeText(activity, "Заметка сохранена", Toast.LENGTH_SHORT).show()
            saveNote()
        } else {
            Toast.makeText(activity, "Заметка не сохранена", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveNote() {
        with(binding) {
            val note = Note(
                0,
                noteAddTitle.text.toString(),
                noteAddDescription.text.toString(),
                noteAddTag.text.toString()
            )

            if (noteAddTitle.text.isNullOrEmpty()) {
                note.title = "Пустой загаловок"

                noteViewModel?.insert(note)
            } else {
                noteViewModel?.insert(note)
            }
        }
    }

    private fun setupViewModel() {
        noteViewModel = ViewModelProvider(this, viewModelProviderFactory)[NoteViewModel::class.java]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        saveNoteToDatabase()
    }

    private fun validations() : Boolean {
        return !(binding.noteAddTitle.text.isNullOrEmpty()
                && binding.noteAddDescription.text.isNullOrEmpty()
                && binding.noteAddTag.text.isNullOrEmpty())
    }

}