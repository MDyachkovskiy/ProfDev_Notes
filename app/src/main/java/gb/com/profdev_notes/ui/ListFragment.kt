package gb.com.profdev_notes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import gb.com.profdev_notes.R
import gb.com.profdev_notes.databinding.FragmentListBinding
import gb.com.profdev_notes.model.datasource.Note
import gb.com.profdev_notes.ui.adapter.NoteAdapter
import gb.com.profdev_notes.ui.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class ListFragment: DaggerFragment(), NoteAdapter.Interaction {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val adapter: NoteAdapter? = null

    private var allNotes: List<Note>? = null

    private lateinit var noteViewModel: NoteViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    //Method #1
    companion object {
        fun newInstance() = ListFragment()
    }

    //Method #2
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        initRecyclerView()
        observerLiveData()
    }

    private fun observerLiveData() {
        noteViewModel.getAllNotes().observe(viewLifecycleOwner) { lisOfNotes ->
            lisOfNotes?.let {
                allNotes = it
                adapter?.swap(it)
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = allNotes?.let { NoteAdapter(it, this@ListFragment) }
        }
        val swipe = ItemTouchHelper(initSwipeToDelete())
        swipe.attachToRecyclerView(binding.recyclerView)
    }

    private fun initSwipeToDelete(): ItemTouchHelper.Callback {
        return object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.RIGHT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(
                viewHolder: RecyclerView.ViewHolder,
                direction: Int
            ) {
                val position = viewHolder.adapterPosition
                val note = allNotes?.get(position)
                note?.let { noteViewModel.delete(it) }
                Toast.makeText(activity, "Заметка удалена", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //Method #3
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Method #4
    private fun setupViewModel() {
        noteViewModel =
            ViewModelProvider(this, viewModelProviderFactory)[NoteViewModel::class.java]
    }

    override fun onItemSelected(position: Int, item: Note) {
        val note = allNotes?.get(position)
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.container, EditFragment.newInstance(note))
            .addToBackStack(null)
            .commit()
    }
}